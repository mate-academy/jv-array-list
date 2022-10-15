package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double DEFAULT_COEFFICIENT = 1.5;
    private static final String INVALID_INDEX_MSG = "The index passed to the method is invalid";
    private int defaultLength = 10;
    private int size;
    private Object[] array = new Object[defaultLength];

    @Override
    public void add(T value) {
        if (size >= array.length) {
            resizing();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INVALID_INDEX_MSG);
        }
        if (size >= array.length) {
            resizing();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexValidity(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexValidity(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexValidity(index);
        return doRemove(index);
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("There is no such element present");
        }
        return doRemove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexValidity(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INVALID_INDEX_MSG);
        }
    }

    private void resizing() {
        defaultLength = (int) (array.length * DEFAULT_COEFFICIENT);
        Object[] arrayNew = new Object[defaultLength];
        System.arraycopy(array, 0, arrayNew, 0, array.length);
        array = arrayNew;
    }

    private T doRemove(int index) {
        T value = (T) array[index];
        size--;
        System.arraycopy(array, index + 1, array, index, size - index);
        return value;
    }
}
