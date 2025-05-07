package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            arrayIncrease(size + 1);
        }

        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == data.length) {
            arrayIncrease(size + 1);
        }

        if (checkIndex(index) || index == size) {
            System.arraycopy(data, index, data, index + 1, size - index);
            data[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is not exists");
        }
    }

    @Override
    public void addAll(List<T> list) {
        int temp = size + list.size();

        if (temp > data.length) {
            arrayIncrease(temp + 1);
        }

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (checkIndex(index)) {
            return data[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is not exists");
        }
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            data[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Cannot set element on index " + index);
        }
    }

    @Override
    public T remove(int index) {
        if (checkIndex(index)) {
            T value = data[index];

            if (index == data.length - 1) {
                data[index] = null;
            } else {
                System.arraycopy(data, index + 1, data, index, size - index);
            }
            size--;

            return value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Cannot remove element on index " + index);
        }
    }

    @Override
    public T remove(T element) {
        int index = findIndex(element);
        if (index != -1) {
            System.arraycopy(data, index + 1, data, index, size - index);
            size--;
        } else {
            throw new NoSuchElementException("Element is not exists");
        }

        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean checkIndex(int index) {
        return index < size && index >= 0;
    }

    private int findIndex(T type) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == type || data[i] != null && data[i].equals(type)) {
                return i;
            }
        }
        return -1;
    }

    private void arrayIncrease(int length) {
        T[] copy = (T[]) new Object[length + (length >> 1)];
        System.arraycopy(data, 0, copy, 0, data.length);
        data = copy;
    }
}
