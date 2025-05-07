package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int capacity;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        size = 0;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index passed is invalid");
        }
        if (size == capacity - 1) {
            growIfNecessary();
        }
        if (index < size) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
        }
        elementData[index] = value;
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
        verifyIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        verifyIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        verifyIndex(index);
        size--;
        T removedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size + 1 - index);
        elementData[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null || elementData[i] != null
                    && elementData[i].equals(element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfNecessary() {
        capacity = capacity + (DEFAULT_CAPACITY >> 1);
        T[] newArray = (T[]) new Object[capacity];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }

    private void verifyIndex(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index " + index + " is invalid.\n"
                    + "Appropriate index values must be less or equal to" + (size - 1)
                    + " and greater than or equal to 0");
        }
    }
}
