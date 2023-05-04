package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_SIZE = 10;
    public static final double EXPANSION_COEFFICIENT = 1.5;
    private T[] storage;
    private int size;

    public ArrayList() {
        storage = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity();
        checkBounds(index, false);
        System.arraycopy(storage, index,
                storage, index + 1,
                size - index);
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
        checkBounds(index);
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkBounds(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBounds(index);
        final T deletedValue = get(index);
        System.arraycopy(storage,
                index + 1,
                storage,
                index,
                size - 1 - index);
        size--;
        return deletedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i <= size; i++) {
            if (storage[i] == element || storage[i] != null && storage[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(generateElementNotFoundErrorMessage(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void ensureCapacity() {
        if (storage.length == size) {
            grow();
        }
    }

    private void grow() {
        int newCapacity = storage.length + (int) (storage.length * EXPANSION_COEFFICIENT);
        T[] newStorage = (T[]) new Object[newCapacity];
        System.arraycopy(storage, 0, newStorage, 0, storage.length);
        storage = newStorage;
    }

    //use inclusive by default
    private void checkBounds(int index) {
        checkBounds(index, true);
    }

    private void checkBounds(int index, boolean inclusive) {
        if ((inclusive ? index >= size : index > size)
                || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    generateErrorMessage(index, size)
            );
        }
    }

    private String generateErrorMessage(int index, int size) {
        return String.format("Index %d out of bounds for array size %d", index, size);
    }

    private String generateElementNotFoundErrorMessage(T element) {
        return "Element " + element + " not found.";
    }
}
