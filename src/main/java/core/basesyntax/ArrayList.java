package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int RESIZE_FACTOR = 1;
    private static final boolean ADD = true;
    private static final boolean DELETE = false;
    private T[] storage;
    private int size;

    public ArrayList() {
        storage = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size >= storage.length) {
            grow();
        }
        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        if (size >= storage.length) {
            grow();
        }
        moveAdd(index, ADD);
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
    public T remove(int index) {
        checkIndex(index);
        T oldValue = storage[index];
        moveAdd(index, DELETE);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (storage[i] == element || (storage[i] != null && storage[i].equals(element))) {
                T oldValue = storage[i];
                remove(i);
                return oldValue;
            }
        }
        throw new NoSuchElementException("Element not found");
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
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index must be: "
                    + "index < size && index >= 0");
        }
    }

    private void grow() {
        int newLength = storage.length + (storage.length >> RESIZE_FACTOR);
        Object[] replaceStorage = new Object[newLength];
        System.arraycopy(storage, 0, replaceStorage, 0, storage.length);
        updateStorage(replaceStorage);
    }

    private void moveAdd(int index, boolean check) {
        Object[] replaceStorage = new Object[storage.length];
        System.arraycopy(storage, 0, replaceStorage, 0, index);
        int srcPos = index + (check ? 0 : 1);
        int destPos = index + (check ? 1 : 0);
        System.arraycopy(storage, srcPos, replaceStorage, destPos, (storage.length - index - 1));
        updateStorage(replaceStorage);
    }

    private void updateStorage(Object[] replaceStorage) {
        storage = (T[]) replaceStorage.clone();
    }
}
