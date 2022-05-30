package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private static final int FIRST_INDEX = 0;
    private static final int NEXT_INDEX = 1;
    private int size;
    private Object[] values;

    {
        values = new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        values[size()] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkCapacity();
        size++;
        checkIndex(index);
        System.arraycopy(values, index, values, index + NEXT_INDEX, size - index - 1);
        values[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int index = 0; index < list.size(); index++) {
            add(list.get(index));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object element = values[index];
        if (index == values.length - 1) {
            values[index] = null;
        } else {
            System.arraycopy(values, index + NEXT_INDEX, values, index, size - index);
        }
        size--;
        return (T) element;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkCapacity() {
        if (values.length == size) {
            grow();
        }
    }

    private void grow() {
        Object[] tmpArray = new Object[size + (size >> 1)];
        System.arraycopy(values, FIRST_INDEX, tmpArray, FIRST_INDEX, size);
        values = tmpArray;
    }

    private void checkIndex(int index) {
        if (index >= size || index < FIRST_INDEX) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private int indexOf(T element) {
        for (int index = 0; index < values.length; index++) {
            if (elementsEquals(values[index], element)) {
                return index;
            }
        }
        throw new NoSuchElementException("No such element:" + element);
    }

    private boolean elementsEquals(Object element1, Object element2) {
        return element1 == element2 || element1 != null && element1.equals(element2);
    }
}
