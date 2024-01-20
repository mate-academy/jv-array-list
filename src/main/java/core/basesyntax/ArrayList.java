package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int capacity = DEFAULT_SIZE;
    private int size;
    private T[] arrayData = (T[]) new Object[DEFAULT_SIZE];

    @Override
    public void add(T value) {
        if (size == capacity) {
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
        if (size == capacity) {
            this.grow();
        }
        System.arraycopy(arrayData, index, arrayData, index + 1, size - index);
        arrayData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
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
        capacity += capacity / 2;
        T[] newArrayData = (T[]) new Object[capacity];
        for (int i = 0; i < arrayData.length; i++) {
            newArrayData[i] = arrayData[i];
        }
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
