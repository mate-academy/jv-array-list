package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_INDEX = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Cannot add value for selected index "
                    + index);
        }
        grow();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
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
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        verifyIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        verifyIndex(index);
        T oldElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return oldElement;
    }

    @Override
    public T remove(T element) {
        T oldElement;
        for (int i = 0; i < size; i++) {
            if ((elements[i] != null && elements[i].equals(element))
                    || (elements[i] == null && element == null)) {
                oldElement = elements[i];
                remove(i);
                return oldElement;
            }
        }
        throw new NoSuchElementException("Element doesn't exist");
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
        if (size >= elements.length) {
            int arraySize = (int) (size * CAPACITY_INDEX);
            T [] newArray = (T[]) new Object[arraySize];
            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
        }
    }

    private void verifyIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }
}
