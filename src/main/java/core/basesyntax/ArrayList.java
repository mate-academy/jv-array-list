package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_INITIAL_ARRAY_CAPACITY = 10;
    private Object[] values;
    private int size;

    public ArrayList() {
        values = new Object[DEFAULT_INITIAL_ARRAY_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            growArray();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index  " + index
                    + "is invalid for size " + size);
        }
        if (size() == values.length) {
            growArray();
        }
        if (index == size()) {
            values[size()] = value;
            size++;
        } else if (index < size()) {
            System.arraycopy(values, index, values, index + 1, values.length - index - 1);
            values[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() + size() > values.length) {
            growArray();
        }
        Object[] valuesFmArrayList = new Object[values.length];
        for (int i = 0; i < list.size(); i++) {
            valuesFmArrayList[i] = list.get(i);
        }
        System.arraycopy(valuesFmArrayList, 0, values, size(), list.size());
        size = size + list.size();
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
        final T removedValue = (T) values[index];
        System.arraycopy(values, index + 1, values, index, values.length - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < size; index++) {
            if ((values[index] == element) || (values[index] != null
                    && values[index].equals(element))) {
                return remove(index);
            }
        }
        throw new NoSuchElementException("Element was not found" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growArray() {
        Object[] newValues = new Object[values.length + values.length / 2];
        System.arraycopy(values, 0, newValues, 0, values.length);
        values = newValues;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index  " + index
                    + "is invalid for size " + size);
        }
    }
}
