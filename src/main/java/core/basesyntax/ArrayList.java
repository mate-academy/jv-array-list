package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double CAPACITY_INCREASE_VALUE = 1.5;
    private static final int INITIAL_CAPACITY = 10;
    private T [] storage;
    private int size;

    public ArrayList() {
        storage = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == storage.length) {
            increaseCapacity();
        }
        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index of element is"
                    + " out of bounds of Array List!");
        }
        if (size == storage.length) {
            increaseCapacity();
        }
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
        T removedItem = storage[index];
        removeElement(index);
        return removedItem;
    }

    @Override
    public T remove(T element) {
        int index = checkElement(element);
        if (index == -1) {
            throw new NoSuchElementException("The element was not find in the Array List");
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

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index of element is"
                    + " out of bounds of Array List!");
        }
    }

    private void increaseCapacity() {
        T [] storageNew = (T[]) new Object[(int)(size * CAPACITY_INCREASE_VALUE)];
        System.arraycopy(storage, 0, storageNew, 0, size);
        storage = storageNew;
    }

    private void removeElement(int index) {
        if (index < (size - 1)) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
        storage[(size - 1)] = null;
        size--;
    }

    private int checkElement(T element) {
        for (int i = 0; i < size; i++) {
            if ((storage[i] == null & element == null)
                    || (storage[i] != null && storage[i].equals(element))) {
                return i;
            }
        }
        return -1;
    }
}
