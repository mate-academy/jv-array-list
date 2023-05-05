package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWING_COEFFICIENT = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            int newLength = (int) (elementData.length * GROWING_COEFFICIENT);
            resize(newLength);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        if (size == elementData.length) {
            int newLength = (int) (elementData.length * GROWING_COEFFICIENT);
            resize(newLength);
        }
        verifyIndex(index);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        set(value, index);
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
        T object = get(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return object;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementsAreEqual(element, elementData[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such item found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void resize(int newLength) {
        if (newLength < size) {
            throw new IllegalArgumentException("New length is less than the current size");
        }
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }

    private void verifyIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private boolean elementsAreEqual(T first, T second) {
        return (first == second) || (second != null && second.equals(first));
    }
}
