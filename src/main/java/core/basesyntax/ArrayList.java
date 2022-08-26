package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] list;
    private int size;

    public ArrayList() {
        size = 0;
        list = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (isOutOfBound(size)) {
            grow();
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkArraySize(index);
        if (isOutOfBound(size)) {
            grow();
        }
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
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
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removedElement = list[index];
        System.arraycopy(list, index + 1, list, index, size - 1 - index);
        set(null, size - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (list[i] == element || list[i] != null && list[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
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
        T[] newArray = (T[]) new Object[size + size / 2];
        System.arraycopy(list, 0, newArray, 0, size);
        list = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " not found. "
                    + "Actual array size: " + size);
        }
    }

    private void checkArraySize(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " not found. "
                    + "Actual array size: " + size);
        }
    }

    private boolean isOutOfBound(int size) {
        return size >= list.length;
    }

}
