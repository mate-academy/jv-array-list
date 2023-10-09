package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private T[] storage;
    private int size;

    public ArrayList() {
        storage = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfStorageFull();
        storage[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index! " + index);
        }
        growIfStorageFull();
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
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T valueToBeRemoved = storage[index];
        System.arraycopy(storage, index + 1, storage, index, --size - index);
        return valueToBeRemoved;
    }

    @Override
    public T remove(T value) {
        for (int i = 0; i < size; i++) {
            if (storage[i] != null && storage[i].equals(value) || storage[i] == value) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such value: " + value);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfStorageFull() {
        if (size == storage.length) {
            T[] newStorage = (T[]) new Object[(int) (storage.length * GROW_FACTOR)];
            System.arraycopy(storage, 0, newStorage, 0, size);
            storage = newStorage;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index "
                    + index + " for size: " + size);
        }
    }
}
