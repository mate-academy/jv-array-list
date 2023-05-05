package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] listElements;
    private int size;

    public ArrayList() {
        listElements = (T[])new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (listElements.length == size) {
            grow();
        }
        listElements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        verifyIndexToAdd(index);
        if (listElements.length == size) {
            grow();
        }
        System.arraycopy(listElements, index, listElements, index + 1, size - index);
        listElements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        if (size + list.size() > listElements.length) {
            grow();
        }
        int lastElementIndex = size;
        for (int i = 0; i < list.size(); i++) {
            listElements[lastElementIndex++] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        verifyIndex(index);
        return listElements[index];
    }

    @Override
    public void set(T value, int index) {
        verifyIndex(index);
        listElements[index] = value;
    }

    @Override
    public T remove(int index) {
        verifyIndex(index);
        T removedElement = listElements[index];
        for (int i = 0; i < listElements.length - 1; i++) {
            listElements[i] = listElements[i + 1];
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        T removedElement;
        for (int i = 0; i < size; i++) {
            if ((listElements[i] != null && listElements[i].equals(element))
                    || element == listElements[i]) {
                removedElement = listElements[i];
                remove(i);
                return removedElement;
            }
        }
        throw new NoSuchElementException("Element not found!");
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
        int oldCapacity = listElements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        listElements = Arrays.copyOf(listElements, newCapacity);
    }

    private void verifyIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds!");
        }
    }

    private void verifyIndexToAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds!");
        }
    }
}
