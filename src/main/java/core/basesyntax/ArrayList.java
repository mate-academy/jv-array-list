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
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds!");
        }
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
        final T removedElement = (T) listElements[index];
        System.arraycopy(listElements, index + 1, listElements, index, size - index - 1);
        size--;
        listElements[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < listElements.length; i++) {
            if ((listElements[i] != null && listElements[i].equals(element))
                    || listElements[i] == element) {
                return remove(i);
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
        if (index < 0 || size <= index) {
            throw new ArrayListIndexOutOfBoundsException("Index [" + index + "] is out of range");
        }
    }
}
