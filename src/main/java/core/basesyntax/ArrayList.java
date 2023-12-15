package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private int size;
    private T[] data;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            extendCapacity();
        }
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index
                    + " for size: " + size);
        }
        if (size == data.length) {
            extendCapacity();
        }
        if (index == size) {
            add(value);
        } else {
            System.arraycopy(data, index, data, index + 1, size - index);
            data[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexValidation(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexValidation(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexValidation(index);
        final T removedObject = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element
                    || data[i] != null && data[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in ArrayList: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void extendCapacity() {
        int newCapacity = (int) (data.length * GROW_FACTOR);
        T[] dataWithNewCapacity = (T[]) new Object[newCapacity];
        System.arraycopy(data, 0, dataWithNewCapacity, 0, data.length);
        data = dataWithNewCapacity;
    }

    private void checkIndexValidation(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index
                    + " for size: " + size);
        }
    }
}
