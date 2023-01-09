package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] storage = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        if (size == storage.length) {
            increaseCapacity();
        }
        storage[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        indexValidation(index, false);

        if (index == size) {
            add(value);
            return;
        }

        if (size == storage.length) {
            increaseCapacity();
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = value;
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
        indexValidation(index, true);
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index, true);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index, true);
        T value = storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[--size] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (element == null ? storage[i] == null : element.equals(storage[i])) {
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

    private void increaseCapacity() {
        T[] newStorage = (T[]) new Object[storage.length + (storage.length >> 1)];
        System.arraycopy(storage,0, newStorage, 0, storage.length);
        storage = newStorage;
    }

    private void indexValidation(int index, boolean isSizeEqualToIndexForbidden) {
        if ((isSizeEqualToIndexForbidden ? index >= size : index > size) || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
              "Index is out of array size. Index: " + index + ". Size: " + size + "."
            );
        }
    }
}
