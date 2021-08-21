package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String BOUNDS_EXCEPTION = "Out of Bounds";
    private static final String NO_ELEMET_EXCEPTION = "No such element exists";
    private static final int MAX_SIZE = 10;
    private Object[] array = new Object[MAX_SIZE];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == array.length) {
            copy(array);
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(BOUNDS_EXCEPTION);
        }
        if (size == array.length) {
            copy(array);
        }
        Object[] newArray = new Object[size + 1];
        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = value;
        System.arraycopy(array, index, newArray, index + 1, size - index);
        size++;
        array = newArray;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object[] newArray = new Object[size - 1];
        final Object output = array[index];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, size - index - 1);
        array = newArray;
        size--;
        return (T) output;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || element != null && element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_ELEMET_EXCEPTION);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void copy(Object[] inputArray) {
        Object[] copiedArray = new Object[inputArray.length * 3 / 2];
        System.arraycopy(inputArray, 0, copiedArray, 0, inputArray.length);
        array = copiedArray;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(BOUNDS_EXCEPTION);
        }
    }
}
