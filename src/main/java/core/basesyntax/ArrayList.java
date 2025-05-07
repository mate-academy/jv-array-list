package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private int size;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        growIfArrayFull();
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
        for (int i = 0; i < size; i++) {
            if (i == index) {
                values[i] = value;
            }
        }
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = values[index];
        int numMoved = size - index - 1;
        System.arraycopy(values, index + 1, values, index, numMoved);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("no such element in ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size == values.length) {
            int capacity = (int) (values.length * GROW_FACTOR);
            T[] newCapacity = (T[]) new Object[capacity];
            System.arraycopy(values, 0, newCapacity, 0, size);
            values = newCapacity;
        }
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
