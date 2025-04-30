package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final double GROWING_FACTOR = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        grow();
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
        final T oldValue = values[index];
        System.arraycopy(values, index + 1,
                values, index, size - index - 1);
        values[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == values[i])
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

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds. Size = " + size);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds. Size = " + size);
        }
    }

    private void grow() {
        if (size == values.length) {
            int newCapacity = (int) (values.length * GROWING_FACTOR);
            T[] newValues = (T[]) new Object[newCapacity];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }
}
