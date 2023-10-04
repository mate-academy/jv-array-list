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
        if (size == storage.length) {
            grow();
        }
        storage[size++] = value;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if ((index < 0) || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index! " + index);
        }
        if (size == storage.length) {
            grow();
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() > (storage.length - size)) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            storage[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        getIndexValidation(index);
        return storage[index];
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        getIndexValidation(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        getIndexValidation(index);
        T valueToBeRemoved = storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;
        return valueToBeRemoved;
    }

    @Override
    public T remove(T value) {
        T valueToBeRemoved = null;
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if ((value == null && storage[i] == null)
                        || (value != null && value.equals(storage[i]))) {
                    valueToBeRemoved = storage[i];
                    size--;
                    System.arraycopy(storage, i + 1, storage, i, size - i);
                    storage[size] = null;
                    break;
                }
            }
            if (valueToBeRemoved == null && value != null) {
                throw new NoSuchElementException("There is no such value in the storage! " + value);
            }
        }
        return valueToBeRemoved;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        T[] newStorage = (T[]) new Object[(int) (storage.length * GROW_FACTOR)];
        System.arraycopy(storage, 0, newStorage, 0, size);
        storage = newStorage;
    }

    private void getIndexValidation(int index) throws ArrayListIndexOutOfBoundsException {
        if ((index < 0) || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index! " + index);
        }
    }
}
