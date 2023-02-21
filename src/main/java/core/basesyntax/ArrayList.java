package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkCapacity();
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d out of bounds. Size of list: %d", index, size)
            );
        }
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
        final T removingElement = get(index);
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
        return removingElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element || (data[i] != null && data[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " not found!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkCapacity() {
        if (size == data.length) {
            growDataLength();
        }
    }

    private void growDataLength() {
        int newCapacity = data.length + (data.length >> 1);
        T[] newData = (T[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d out of bounds. Size of list: %d", index, size)
            );
        }
    }
}
