package core.basesyntax;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 8;
    private T[] storage = (T[])new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        if (size == storage.length) {
            increase(size + 1);
        }
        storage[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (checkIndex(index)) {
            if (size == storage.length) {
                increase(size + 1);
            }
            for (int i = size; i > index; i--) {
                storage[i] = storage[i - 1];
            }
            storage[index] = value;
            size++;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Wrong index");
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() + size > storage.length) {
            increase(list.size() + size + 1);
        }
        int newSize = list.size() + size;
        for (int i = size, j = 0; i < newSize; i++, j++) {
            storage[i] = list.get(j);
        }
        size = newSize;
    }

    private void increase(int lenght) {
        int capacity = lenght + (lenght >> 1);
        T[] newStorage = (T[]) new Object[capacity];
        System.arraycopy(storage,0, newStorage,0, storage.length);
        storage = newStorage;
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
    public T remove(int index) {

        return null;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean checkIndex(int index) {
        return index >= 0 && index < size;
    }
}
