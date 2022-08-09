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
        growArray();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index  " + index
                    + "is invalid for size " + size);
        }
        growArray();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = (T) value;
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
        validateIndex(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T removedValue = (T) values[index];
        System.arraycopy(values, index + 1, values, index, values.length - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if (element == null && values[i] == null || values[i] != null
                    && values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element " + element);
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
        if (values.length == size) {
            Object newArray = new Object[(int) (size() * 1.5)];
            System.arraycopy(values, 0, newArray, 0, values.length);
            values = (T[]) newArray;
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index  " + index
                    + "is invalid for size " + size);
        }
    }
}
