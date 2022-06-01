package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] values;

    public ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            values = increaseCapacity();
        }
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        validIndex(index);
        if (size == values.length) {
            values = increaseCapacity();
        }
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
        validIndex(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        validIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        validIndex(index);
        T element = (T) values[index];
        System.arraycopy(values, index + 1, values, index, size - 1 - index);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] != null && values[i].equals(element)
                    || values[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] increaseCapacity() {
        int oldCapacity = values.length;
        int newCapacity = oldCapacity + oldCapacity / 2;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(values, 0, newArray, 0, oldCapacity);
        return newArray;
    }

    private void validIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index: " + index + " is invalid");
        }
    }
}
