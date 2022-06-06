package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private static final int ADD_ID = 1;
    private static final int REMOVE_ID = 0;
    private T[] storage;
    private int size;
    private int currentCapacity;

    {
        storage = (T[]) new Object[INITIAL_SIZE];
        currentCapacity = storage.length;
    }

    @Override
    public void add(T value) {
        if (size == currentCapacity) {
            resize();
        }
        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (size == currentCapacity) {
            resize();
        }
        copy(index, ADD_ID);
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
        T removed = storage[index];
        copy(index, REMOVE_ID);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i] == element || (storage[i] != null && storage[i].equals(element))) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("We cant remove element which doesn't "
                    + "exist in the list");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        T[] temporary = (T[]) new Object[(int) (currentCapacity * 1.5)];
        System.arraycopy(storage, 0, temporary, 0, currentCapacity);
        storage = temporary;
        currentCapacity = (int) (currentCapacity * 1.5);
    }

    private void copy(int index, int operation) {
        if (operation == ADD_ID) {
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        if (operation == REMOVE_ID) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        }
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("You have passed incorrect index,"
                    + " index < 0 OR index > size");
        }
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException("You have passed incorrect index,"
                    + "index must be smaller then size, actual index == size");
        }
    }
}
