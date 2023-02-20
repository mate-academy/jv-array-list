package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_SIZE = 10;
    public static final double EXPANSION_COEFFICIENT = 1.5;
    private T[] storage;
    private int occupiedSpace = 0;

    public ArrayList() {
        storage = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        add(value, occupiedSpace);
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity(1);
        checkBounds(index, false);
        System.arraycopy(storage, index,
                storage, index + 1,
                occupiedSpace - index);
        storage[index] = value;
        occupiedSpace++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkBounds(index, true);
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkBounds(index, true);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBounds(index, true);
        T deletedValue = get(index);
        System.arraycopy(storage,
                index + 1,
                storage,
                index,
                occupiedSpace - 1 - index);
        occupiedSpace--;
        return deletedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i <= occupiedSpace; i++) {
            if (storage[i] == element || storage[i] != null && storage[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(generateElementNotFoundErrorMessage(element));
    }

    @Override
    public int size() {
        return occupiedSpace;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void ensureCapacity(int freeSpaceNeeded) {
        if (storage.length - occupiedSpace < freeSpaceNeeded) {
            grow();
            ensureCapacity(freeSpaceNeeded);
        }
    }

    private void grow() {
        storage = Arrays.copyOf(storage, (int) (storage.length * EXPANSION_COEFFICIENT));
    }

    private void checkBounds(int index, boolean inclusive) {
        if ((inclusive ? index >= occupiedSpace : index > occupiedSpace)
                || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    generateErrorMessage(index, occupiedSpace)
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
