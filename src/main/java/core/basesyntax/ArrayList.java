package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            resize();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }
        if (size == values.length) {
            resize();
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
        size--;
        T value = values[index];
        System.arraycopy(values, index + 1, values, index, size - index);
        return value;
    }

    @Override
    public T remove(T element) {
        T result;
        for (int i = 0; i < size; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                result = values[i];
                remove(i);
                return result;
            }
        }
        throw new NoSuchElementException("There is no such element "
                + "<" + element + ">" + " present in ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void trimToSize() {
        T[] data = values;
        values = (T[]) new Object[size];
        System.arraycopy(data, 0, values, 0, size);
    }

    private void resize() {
        T[] data = values;
        values = (T[]) new Object[size + (INITIAL_CAPACITY >> 1)];
        System.arraycopy(data, 0, values, 0, size);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }
    }
}
