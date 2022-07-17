package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_VALUE = 1.5;
    private T[] storage;
    private int size;

    public ArrayList() {
        storage = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == storage.length) {
            growCapacity((int) (size * GROW_VALUE));
        }
        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        if (size == storage.length) {
            growCapacity((int) (size * GROW_VALUE));
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int requiredCapacity = list.size() + size;
        if (requiredCapacity > storage.length) {
            growCapacity(requiredCapacity);
        }
        for (int i = 0; i < list.size(); i++) {
            storage[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkIndexOutOfBoundException(index);
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexOutOfBoundException(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexOutOfBoundException(index);
        T element = storage[index];
        removeElement(index);
        return element;
    }

    @Override
    public T remove(T element) {
        int index = getElementIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("No such element");
        }
        removeElement(index);
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

    private void growCapacity(int increaseSize) {
        T[] newStorage = (T[]) new Object[(int) (increaseSize)];
        System.arraycopy(storage, 0, newStorage, 0, size);
        storage = newStorage;
    }

    private int getElementIndex(T element) {
        for (int i = 0; i < size; i++) {
            if ((storage[i] == null && element == null)
                    || (storage[i] != null && storage[i].equals(element))) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndexOutOfBoundException(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    private void removeElement(int index) {
        if (index < (size - 1)) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
        storage[(size - 1)] = null;
        size--;
    }
}
