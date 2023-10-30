package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private Object[] values;
    private int size;
    private T oldValue;

    public ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }

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
        grow();

        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            add(element);
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

        oldValue = (T)values[index];
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        oldValue = (T)values[index];

        for (int i = index; i < size - 1; i++) {
            values[i] = values[i + 1];
        }

        size--;
        values[size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if ((element == null && values[i] == null)
                    || (element != null && element.equals(values[i]))) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new NoSuchElementException("Element not found " + element);
        }

        return remove(index);
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
            Object[] newValues = new Object[newCapacity];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }
    }
}
