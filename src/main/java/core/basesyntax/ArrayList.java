package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_MULTIPLIER = 1.5;
    private int capacity;
    private int size;
    private T[] values;

    public ArrayList() {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        values = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size + 1);
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
        checkIndex(index, size);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T value = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (compareElements(element, values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index, int upperBound) {
        if (index < 0 || index >= upperBound) {
            throw new ArrayListIndexOutOfBoundsException("Trying to operate"
                    + " with invalid index: " + index);
        }
    }

    private void growIfArrayFull() {
        if (size == capacity) {
            capacity *= CAPACITY_MULTIPLIER;
            T[] newValues = (T[]) new Object[capacity];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }

    private Boolean compareElements(T element1, T element2) {
        return element1 == null ? element2 == null : element1.equals(element2);
    }
}
