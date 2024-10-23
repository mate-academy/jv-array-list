package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double CAPACITY_INDEX = 1.5;

    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            grow();
        }
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds for list with size " + size);
        }
        if (size == values.length) {
            grow();
        }
        System.arraycopy(values, index, values, index + 1, size - index);
        size++;
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
        T element = (T) values[index];
        System.arraycopy(values, index + 1, values, index, --size - index);
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == values[i] || (element != null && element.equals(values[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element found in list: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int newCapacity = (int) (this.size * CAPACITY_INDEX);
        T[] newValues = (T[]) new Object[newCapacity];
        System.arraycopy(values, 0, newValues, 0, size);
        values = newValues;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds for list with size " + size);
        }
    }
}
