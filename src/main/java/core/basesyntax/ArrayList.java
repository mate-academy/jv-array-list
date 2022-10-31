package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] storage;
    private int size;

    public ArrayList() {
        storage = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkLength();
        storage[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            checkLength();
            System.arraycopy(storage, index, storage,index + 1, size - index);
            storage[index] = value;
            size++;
        } else {
            checkIndex(index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = list.size() + size;
        if (newSize > storage.length) {
            changeLength(newSize);
        }
        for (int i = size, j = 0; i < newSize; i++, j++) {
            storage[i] = list.get(j);
        }
        size = newSize;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T tmp = storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
        storage[--size] = null;
        return tmp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; ++i) {
            if (element == storage[i] || storage[i] != null && storage[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not founded");
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        storage[index] = value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkLength() {
        if (size == storage.length) {
            changeLength(size);
        }
    }

    private void changeLength(int length) {
        int capacity = length + (length >> 1);
        T[] newStorage = (T[]) new Object[capacity];
        System.arraycopy(storage,0, newStorage,0, size);
        storage = newStorage;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
