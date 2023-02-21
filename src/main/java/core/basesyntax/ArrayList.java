package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] storage = new Object[DEFAULT_CAPACITY];
    private int storageSize;

    @Override
    public void add(T value) {
        checkStorageSize();
        storage[storageSize] = value;
        storageSize++;
    }

    @Override
    public void add(T value, int index) {
        checkStorageSize();
        if (index < 0 || index > storageSize) {
            throw new ArrayListIndexOutOfBoundsException("Insert index: "
                    + index + "is not valid. Please try again ");
        }
        System.arraycopy(storage, index, storage, index + 1, storageSize - index);
        storage[index] = value;
        storageSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexValidation(index);
        return (T) storage[index];
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
        throw new NoSuchElementException("Can't find the element " + element);
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
        if (index < 0 || index > storageSize - 1) {
            throw new ArrayListIndexOutOfBoundsException("Insert index: "
                    + index + "is not valid. Please try again ");
        }
    }

    private boolean isEquals(Object a, Object b) {
        return a == b || a != null && a.equals(b);
    }

    private void checkStorageSize() {
        if (storageSize == storage.length) {
            Object[] newStorage = new Object[storage.length + storageSize];
            System.arraycopy(storage, 0, newStorage, 0, storageSize);
            storage = newStorage;
        }
    }
}
