package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] dataStorage;

    public ArrayList() {
        dataStorage = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkSizeForGrow();
        dataStorage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndex(index);
        }
        checkSizeForGrow();
        System.arraycopy(dataStorage, index, dataStorage, index + 1, size - index);
        dataStorage[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > dataStorage.length - size) {
            grow(size + list.size());
        }
        T[] newStorage = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            newStorage[i] = list.get(i);
        }
        System.arraycopy(newStorage, 0, dataStorage, size, list.size());
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return dataStorage[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        dataStorage[index] = value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (dataStorage[i] == element) {
                return remove(i);
            }
        }
        for (int i = 0; i < size; i++) {
            if (element != null && element.equals(dataStorage[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T object = dataStorage[index];
        size--;
        System.arraycopy(dataStorage, index + 1, dataStorage, index, size - index);
        dataStorage[size] = null;
        return object;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Wrong index: " + index + ", current size:" + size);
        }
    }

    private void checkSizeForGrow() {
        if (size == dataStorage.length) {
            grow();
        }
    }

    private void grow() {
        int oldCapacity = dataStorage.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        grow(newCapacity);
    }

    private void grow(int newCapacity) {
        T[] newStorage = (T[]) new Object[newCapacity];
        System.arraycopy(dataStorage, 0, newStorage, 0, size);
        dataStorage = newStorage;
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
