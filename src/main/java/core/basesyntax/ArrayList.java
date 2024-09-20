package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_MULTIPLIER = 1.5;

    private T[] storage;

    private int currentCapacity;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.storage = (T[]) new Object[DEFAULT_CAPACITY];
        this.currentCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        if (size == currentCapacity) {
            resizeStorage();
        }

        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, false);

        if (size == currentCapacity) {
            resizeStorage();
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
        checkIndex(index, true);

        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, true);

        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, true);

        T removedValue = storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[size - 1] = null;
        size--;

        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (storage[i] == element
                    || element != null
                    && element.equals(storage[i])
            ) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("No such element in list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void resizeStorage() {
        int newCapacity = (int) (currentCapacity * CAPACITY_MULTIPLIER);

        T[] newStorage = (T[]) new Object[newCapacity];

        System.arraycopy(storage, 0, newStorage, 0, currentCapacity);

        storage = newStorage;
        currentCapacity = newCapacity;
    }

    private void checkIndex(int index, boolean isSizeIndexForbidden) {
        if (index < 0 || index > size || (isSizeIndexForbidden && index == size)) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index must be between 0 and " + (size)
            );
        }
    }
}
