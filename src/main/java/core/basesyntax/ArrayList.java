package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWING_COEFFICIENT = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow(values.length);
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        grow(values.length);
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
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
        checkIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T value = (T) values[index];
        if (size > index) {
            System.arraycopy(values, index + 1, values, index, size - 1 - index);
        }
        values[size - 1] = null;
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("The element " + element + " is not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow(int oldCapacity) {
        if (size == values.length) {
            int newCapacity = (int) (oldCapacity * GROWING_COEFFICIENT);
            T[] newValues = (T[]) new Object[newCapacity];
            System.arraycopy(values, 0, newValues, 0, values.length);
            values = newValues;
        }
    }

    public void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }
}
