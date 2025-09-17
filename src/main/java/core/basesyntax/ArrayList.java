package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int STARTER_SIZE = 10;
    private static final int STARTER_INDEX = 0;
    private static final String INDEX_OUT_OF_BOUND_MESSAGE = " - index out of bound!";
    private T[] storage;
    private int size;

    public ArrayList() {
        storage = (T[]) new Object[STARTER_SIZE];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIfIndexFits(index);
        growIfArrayFull();
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = STARTER_INDEX; i < list.size(); i++) {
            growIfArrayFull();
            storage[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIfIndexOfBounds(index);
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexOfBounds(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexOfBounds(index);
        final T removedValue = storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = STARTER_INDEX; i < size; i++) {
            if (checkIfElementExist(element, storage[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size == storage.length) {
            T[] updatedArray = (T[]) new Object[(int) (size * 1.5)];
            System.arraycopy(storage, STARTER_INDEX, updatedArray, STARTER_INDEX, size);
            storage = updatedArray;
        }
    }

    private void checkIfIndexOfBounds(int index) {
        if (index < STARTER_INDEX || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(index + INDEX_OUT_OF_BOUND_MESSAGE);
        }
    }

    private void checkIfIndexFits(int index) {
        if (index < STARTER_INDEX || index > size) {
            throw new ArrayListIndexOutOfBoundsException(index + INDEX_OUT_OF_BOUND_MESSAGE);
        }
    }

    private boolean checkIfElementExist(T element, T elementFromStorage) {
        return elementFromStorage == element
                || elementFromStorage != null && elementFromStorage.equals(element);
    }
}
