package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double SIZE_SCALING = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (values.length == size) {
            grow();
        }
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        if (values.length == size) {
            grow();
        }
        checkIndex(index);
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
    public T remove(int index) {
        checkIndex(index);
        T removeElement = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (values[i] == element
                    || element != null
                    && element.equals(values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(element + " element was not found.");
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return values[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(index + " index is not valid for size "
                    + size);
        }
    }

    private void grow() {
        T[] copy = (T[]) new Object[(int) (values.length * SIZE_SCALING)];
        System.arraycopy(values, 0, copy, 0, values.length);
        values = copy;
    }
}
