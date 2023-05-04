package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] storage;
    private int storageSize;

    public ArrayList() {
        storage = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, storageSize);
    }

    @Override
    public void add(T value, int index) {
        checkStorageSize();
        if (index < 0 || index > storageSize) {
            throw new ArrayListIndexOutOfBoundsException("Can't add value on inserted index: "
                    + index + "Your index is not valid. Please try again ");
        }
        System.arraycopy(storage, index, storage, index + 1, storageSize - index);
        storage[index] = value;
        storageSize++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        indexValidation(index);
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        T removedValueByIndex = (T) storage[index];
        System.arraycopy(storage, index + 1, storage, index, storageSize - index - 1);
        storageSize--;
        return removedValueByIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < storageSize; i++) {
            if (isEquals(storage[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Removing element doesn't exist, try again.");
    }

    @Override
    public int size() {
        return storageSize;
    }

    @Override
    public boolean isEmpty() {
        return storageSize == 0;
    }

    private void indexValidation(int index) {
        if (index < 0 || index >= storageSize) {
            throw new ArrayListIndexOutOfBoundsException("Inserted index: "
                    + index + "is not valid. Can't figure operation. Please try again ");
        }
    }

    private boolean isEquals(T a, T b) {
        return a == b || a != null && a.equals(b);
    }

    private void checkStorageSize() {
        if (storageSize == storage.length) {
            T[] newStorage = (T[]) new Object[storage.length + (storageSize >> 1)];
            System.arraycopy(storage, 0, newStorage, 0, storageSize);
            storage = newStorage;
        }
    }
}
