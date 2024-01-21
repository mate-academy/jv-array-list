package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] arrayData;

    public ArrayList() {
        arrayData = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == arrayData.length) {
            this.grow();
        }
        arrayData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of size " + size);
        }
        if (size == arrayData.length) {
            grow();
        }
        System.arraycopy(arrayData, index, arrayData, index + 1, size - index);
        arrayData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }

    }

    @Override
    public T get(int index) {
        checkIndexBound(index);
        return arrayData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBound(index);
        arrayData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBound(index);
        T elementToRemove = arrayData[index];
        System.arraycopy(arrayData, index + 1, arrayData, index, size - index - 1);
        size--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        int index = this.getIndex(element);
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

    private void grow() {
        int capacity = arrayData.length;
        capacity += arrayData.length / 2;
        T[] newArrayData = (T[]) new Object[capacity];
        System.arraycopy(arrayData, 0, newArrayData, 0, size);
        arrayData = newArrayData;
    }

    private int getIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (arrayData[i] == element || arrayData[i] != null && arrayData[i].equals(element)) {
                return i;
            }
        }
        throw new NoSuchElementException("Can`t find element " + element);
    }

    private void checkIndexBound(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of size " + size);
        }
    }
}
