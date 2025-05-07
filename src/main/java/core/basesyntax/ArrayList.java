package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double FACTOR_SIZE = 1.5;
    private static final int DEFAULT_SIZE = 10;
    private T[] data;
    private int size;

    public ArrayList() {
        this.data = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkSize();
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index [" + index + "] out of bounds."
                    + "Must be: 0 <= [" + index + "] <= current size ([" + size + "])");
        }
        checkSize();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
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
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T elem = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return elem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (isEquals(element, data[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkSize() {
        if (size == data.length) {
            grow();
        }
    }

    private void grow() {
        int newLength = (int) (data.length * FACTOR_SIZE);
        T[] newData = (T[]) new Object[newLength];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index [" + index + "] out of bounds."
            + "Must be: 0 <= [" + index + "] < current size ([" + size + "])");
        }
    }

    private boolean isEquals(T first, T second) {
        return first == second || (first != null && first.equals(second));
    }
}
