package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_SIZE = 1.5;
    private int size;
    private T[] data;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkSize();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexToAdd(index);
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
        final T item = data[index];
        System.arraycopy(data, index + 1, data, index, size - 1 - index);
        size--;
        data[size] = null;
        return item;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element || (data[i] != null) && data[i].equals(element)) {
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

    private void grow() {
        T[] newItems = (T[]) new Object[(int) (data.length * INCREASE_SIZE)];
        System.arraycopy(data, 0, newItems, 0, size);
        data = newItems;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index of the bounds "
                    + size + " Check your input index! "
                    + index);
        }
    }

    private void checkIndexToAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index of the bounds "
                    + size + " Check your input index! "
                    + index);
        }
    }

    private void checkSize() {
        if (size == data.length) {
            grow();
        }
    }
}
