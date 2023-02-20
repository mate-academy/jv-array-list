package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] storage;
    private int size;

    public ArrayList() {
        storage = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new RuntimeException("Incorrect capacity value " + capacity);
        }
        storage = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        checkFreeSpace();
        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index " + index
                    + " is out of bound for length " + size);
        }
        checkFreeSpace();
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = value;
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
        return (T) storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removedElement = (T) storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == storage[i] || (storage[i] != null && storage[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkFreeSpace() {
        if (size == storage.length) {
            increaseCapacity();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index " + index
                    + " is out of bound for length " + size);
        }
    }

    private void increaseCapacity() {
        int newCapacity = storage.length / 2 + storage.length;
        T[] destination = (T[]) new Object[newCapacity];
        System.arraycopy(storage, 0, destination,0, size);
        storage = destination;
    }
}
