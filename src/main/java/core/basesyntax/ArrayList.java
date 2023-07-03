package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            grow();
        }
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        indexExist(index);
        if (size == data.length) {
            grow();
        }
        if (index < size) {
            System.arraycopy(data, index, data, index + 1, size - index);
        }
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
        elementExist(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        elementExist(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        elementExist(index);
        T value = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if ((data[i] == null && element == null)
                    || (data[i] != null && data[i].equals(element))) {
                T value = data[i];
                remove(i);
                return value;
            }
        }
        throw new NoSuchElementException("Element: " + element);
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
        int newSize = size + (size >> 1);
        T[] newData = (T[]) new Object[newSize];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    private void indexExist(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    index + " Index out of bound exception for index");
        }
    }

    private void elementExist(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Element does not exist for this index: " + index);
        }
    }
}
