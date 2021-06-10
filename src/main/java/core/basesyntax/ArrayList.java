package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] storage;
    private int size;

    public ArrayList() {
        storage = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == storage.length) {
            grow();
        }
        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
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
        checkForAdd(index);
        return (T) storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkForAdd(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        checkForAdd(index);
        T oldValue = (T) storage[index];
        if (size - 1 > index) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        T oldValue;
        for (int i = 0; i < size; i++) {
            if (storage[i] == element || storage[i] != null && storage[i].equals(element)) {
                oldValue = element;
                System.arraycopy(storage, i + 1, storage, i, size - i);
                size--;
                return oldValue;
            }
        }
        throw new NoSuchElementException();
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
        int oldCapacity = storage.length;
        int newCapacity = oldCapacity + oldCapacity / 2;
        storage = Arrays.copyOf(storage, newCapacity);
    }

    private void checkForAdd(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
