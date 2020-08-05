package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 15;
    private T[] storage;
    private int size;

    public ArrayList() {
        this.storage = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private void checkCapacity() {
        if (storage.length == size) {
            storage = ensureCapacity();
        }
    }

    private T[] ensureCapacity() {
        int newCapacity = storage.length + storage.length >> 1;
        T[] newStorage = (T[]) new Object[newCapacity + 1];
        System.arraycopy(storage, 0, newStorage, 0, size);
        return newStorage;
    }

    @Override
    public void add(T value) {
        checkCapacity();
        storage[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            indexCheck(index);
            checkCapacity();
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() + size > storage.length) {
            storage = ensureCapacity();
        }
        for (int i = 0; i < list.size(); i++) {
            storage[size++] = list.get(i);
        }
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (storage[i] == t || storage[i] != null && storage[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T removed = storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - index);
        storage[--size] = null;
        return removed;
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
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

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
