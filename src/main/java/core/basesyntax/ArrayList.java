package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForIndexAdd(index);
        ensureCapacity();
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("The list cannot be null!");
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("The list cannot be empty!");
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return oldValue;

    }

    @Override
    public T remove(T element) {
        if (element == null) {
            size--;
            return element;
        }

        for (int i = 0; i < size; i++) {
            if (element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Failed to remove element because it was not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == elementData.length) {
            grow();
        }
    }

    private void grow() {
        int newCapacity = (int) (elementData.length * GROW_FACTOR);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound: " + index);
        }
    }

    private void checkIndexForIndexAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound: " + index);
        }
    }
}

