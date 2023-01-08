package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] storage = (T[]) new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == storage.length) {
            T[] newStorage = (T[]) new Object[storage.length + (storage.length >> 1)];
            for (int i = 0; i < storage.length; i++) {
                newStorage[i] = storage[i];
            }
            storage = newStorage;
        }
        storage[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of array size");
        }

        if (index == size) {
            add(value);
            return;
        }

        if (size == storage.length) {
            T[] newStorage = (T[]) new Object[storage.length + (storage.length >> 1)];
            for (int i = 0; i < index; i++) {
                newStorage[i] = storage[i];
            }
            newStorage[index] = value;
            for (int i = index; i < storage.length; i++) {
                newStorage[i + 1] = storage[i];
            }
            storage = newStorage;
        } else {
            for (int i = size - 1; i >= index; i--) {
                storage[i + 1] = storage[i];
            }
            storage[index] = value;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of array size");
        }
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of array size");
        }

        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of array size");
        }
        T value = storage[index];
        for (int i = index + 1; i < size; i++) {
            storage[i - 1] = storage[i];
        }
        storage[--size] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, storage[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("No such Element");
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
}
