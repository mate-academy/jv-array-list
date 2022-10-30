package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 8;
    private T[] storage;
    private int size;

    public ArrayList(){
        storage = (T[]) new Object[DEFAULT_CAPACITY];
    }
    @Override
    public void add(T value) {
        if (size == storage.length) {
            changeLength(size + 1);
        }
        storage[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (checkIndex(index) || index == size) {
            if (size == storage.length) {
                changeLength(size);
            }
            System.arraycopy(storage, index, storage,index + 1, size - index);
            storage[index] = value;
            size++;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Wrong index");
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() + size > storage.length) {
            changeLength(list.size() + size);
        }
        int newSize = list.size() + size;
        for (int i = size, j = 0; i < newSize; i++, j++) {
            storage[i] = list.get(j);
        }
        size = newSize;
    }

    @Override
    public T remove(int index) {
        T tmp;
        if (checkIndex(index)) {
            tmp = storage[index];
            System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
            storage[--size] = null;
            changeLength(size);
            return tmp;
        }
        throw new ArrayListIndexOutOfBoundsException("Wrong index");
    }

    @Override
    public T remove(T element) {
        if (element != null) {
            for (int i = 0; i < size; ++i) {
                if (storage[i] != null && storage[i].equals(element)) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; ++i) {
                if (storage[i] == null) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("Element not founded");
    }

    @Override
    public T get(int index) {
        if (checkIndex(index)) {
            return storage[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Wrong index");
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            storage[index] = value;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Wrong index");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void changeLength(int length) {
        int capacity = length + (length >> 1);
        T[] newStorage = (T[]) new Object[capacity];
        System.arraycopy(storage,0, newStorage,0, size);
        storage = newStorage;
    }

    private boolean checkIndex(int index) {
        return index >= 0 && index < size;
    }
}
