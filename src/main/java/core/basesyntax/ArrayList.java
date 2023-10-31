package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexExclusive(index);

        grow();

        if (index == size) {
            values[size++] = value;
        } else {
            System.arraycopy(values, index, values, index + 1, size - index);
            values[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            add(element);
        }
    }

    @Override
    public T get(int index) {
        checkIndexInclusive(index);

        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexInclusive(index);

        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexInclusive(index);

        final T removedValue = (T)values[index];

        for (int i = index; i < size - 1; i++) {
            values[i] = values[i + 1];
        }

        size--;
        values[size] = null;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if ((element == null && values[i] == null)
                    || (element != null && element.equals(values[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found " + element);
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
        if (size == values.length) {
            int newCapacity = (int)(values.length * GROWTH_FACTOR);
            T[] newValues = (T[]) new Object[newCapacity];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }

    private void checkIndexInclusive(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }
    }

    private void checkIndexExclusive(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }
    }
}
