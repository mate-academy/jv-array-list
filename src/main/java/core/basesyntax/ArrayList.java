package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private int size;
    private T[] storage;

    public ArrayList() {
        this.storage = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (isFull()) {
            resizeStorage();
        }
        storage[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, true);
        if (isFull()) {
            resizeStorage();
        }
        if (index < size) {
            moveRight(index);
        }
        storage[index] = value;
        ++size;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, false);
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, false);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);
        return remove(storage[index]);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage[i], element)) {
                moveLeft(i);
                --size;
                return element;
            }
        }
        throw new NoSuchElementException("Can't find element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == storage.length;
    }

    private void checkIndex(int index, boolean isAdding) {
        int upperBound = isAdding ? size : size - 1;
        if (index > upperBound || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Out of index: trying to work with %s position but size is %s",
                            index, size));
        }
    }

    private void resizeStorage() {
        T[] storageCopy = (T[]) new Object[size];
        System.arraycopy(storage, 0, storageCopy, 0, size);
        storage = (T[]) new Object[storage.length + (storage.length >> 1)];
        System.arraycopy(storageCopy, 0, storage, 0, size);
    }

    private void moveLeft(int fromIndex) {
        if (fromIndex == size - 1) {
            storage[fromIndex] = null;
            return;
        }
        System.arraycopy(storage, fromIndex + 1, storage, fromIndex, size - fromIndex);
    }

    private void moveRight(int fromIndex) {
        System.arraycopy(storage, fromIndex, storage, fromIndex + 1, size - fromIndex);
    }
}
