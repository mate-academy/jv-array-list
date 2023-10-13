package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String INVALID_INDEX = "Invalid index: ";
    private static final String NOT_FOUND_VALUE = "Not found element like: ";
    private static final int CAPACITY = 10;
    private Object[] storage = new Object[CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        increaseCapacity();

        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(INVALID_INDEX + index);
        }
        increaseCapacity();

        System.arraycopy(storage, index, storage, index + 1,size - index);
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
        return (T) storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = get(index);

        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
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
        if (size == storage.length) {
            int oldCapacity = storage.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            Object[] increasedStorage = new Object[newCapacity];
            System.arraycopy(storage, 0, increasedStorage, 0, size);
            storage = increasedStorage;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INVALID_INDEX + index);
        }
    }

    private int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (value == storage[i]
                    || (value != null && value.equals(storage[i]))) {
                return i;
            }
        }
        throw new NoSuchElementException(NOT_FOUND_VALUE + value);
    }
}
