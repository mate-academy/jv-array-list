package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] values;
    private int size;

    public ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize(size);
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        rangeCheck(index);
        resize(size);
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resize(size);
            values[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index + 1);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index + 1);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index + 1);
        Object removedValue = values[index];
        int tailLength = size - index - 1;
        if (tailLength > 0) {
            System.arraycopy(values, index + 1, values, index, tailLength);
        }
        values[--size] = null;
        return (T) removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == element || (values[i] != null && values[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void rangeCheck(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void resize(int actualSize) {
        if (actualSize == values.length) {
            Object[] newValues = new Object[values.length + values.length << 1];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }
}
