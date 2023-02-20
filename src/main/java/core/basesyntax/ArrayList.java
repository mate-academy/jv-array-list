package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] storage;
    private int size;

    public ArrayList() {
        this.storage = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index must be from 0 to " + size);
        }
        checkCapacity();
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            storage[size] = list.get(i);
            size++;
        }
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
    public T remove(int index) {
        if (size == 0 || index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index " + index);
        }
        T oldObject = storage[index];
        if (index == size - 1) {
            size--;
            return oldObject;
        } else {
            System.arraycopy(storage, index + 1, storage, index, size);
            size--;
        }
        return oldObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (storage[i] == element || (storage[i] != null && storage[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There are no such elements in the storage!");
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
        int newCapacity = size + (size >> 1);
        T[] biggerStorage = (T[]) new Object[newCapacity];
        System.arraycopy(storage, 0, biggerStorage, 0, size);
        storage = biggerStorage;
    }

    private void checkCapacity() {
        if (size == storage.length) {
            grow();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index " + index);
        }
    }
}
