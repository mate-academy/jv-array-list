package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String NOT_FOUND_INDEX_MESSAGE = "Not found index: ";
    private static final String NOT_FOUND_VALUE_MESSAGE = "Not found element with value: ";
    private Object[] storage = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        increaseStorage();
        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        size++;
        validateIndex(index);
        increaseStorage();
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return (T) storage[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        final T value = get(index);
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[size - 1] = null;
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        remove(index);
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void validateIndex(int index) {
        if ((index < 0) || (index > size - 1)) {
            throw new ArrayListIndexOutOfBoundsException(NOT_FOUND_INDEX_MESSAGE + index);
        }
    }

    private void increaseStorage() {
        if (size == storage.length) {
            int oldCapacity = storage.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            Object [] increasedStorage = new Object[newCapacity];
            System.arraycopy(storage, 0, increasedStorage, 0, size);
            storage = increasedStorage;
        }
    }

    private int indexOf(T value) {
        for (int i = 0; i < size(); i++) {
            if ((value == storage[i])
                    || ((value != null) && (value.equals(storage[i])))) {
                return i;
            }
        }
        throw new NoSuchElementException(NOT_FOUND_VALUE_MESSAGE + value);
    }
}
