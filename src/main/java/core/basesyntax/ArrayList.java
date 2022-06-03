package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_LENGTH = 10;
    private int size;
    private Object[] values;

    public ArrayList() {
        values = new Object[INITIAL_LENGTH];
    }

    @Override
    public void add(T value) {
        checkLength();
        values[size()] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == index) {
            add(value);
            return;
        }
        size++;
        checkValidIndex(index);
        checkLength();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkValidIndex(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkValidIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkValidIndex(index);
        T removedValue = (T) values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        int index = getIndexByValue(element);
        T removedValue = (T) values[index];
        System.arraycopy(values, index + 1, values, index, size - index);
        size--;
        return removedValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void checkLength() {
        if (values.length == size) {
            scaleArray();
        }
    }

    private void scaleArray() {
        Object[] copyArray = new Object[values.length + (values.length >> 1)];
        System.arraycopy(values, 0, copyArray, 0, size);
        values = copyArray;
    }

    private void checkValidIndex(int index) {
        if (index >= size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " is invalid for size " + size);
        }
    }

    private int getIndexByValue(T value) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == value || values[i] != null && values[i].equals(value)) {
                return i;
            }
        }
        throw new NoSuchElementException("No such  " + value + " element present");
    }

}
