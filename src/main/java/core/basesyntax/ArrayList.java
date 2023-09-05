package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[])new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfNeeded();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        verifyIndexForAdd(index);
        growIfNeeded();
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
        final T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        elements[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if ((elements[i] != null && elements[i].equals(element))
                    || elements[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " not found!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfNeeded() {
        if (size == elements.length) {
            T[] resizeElementsArray = (T[]) new Object[(int) (elements.length * 1.5)];
            System.arraycopy(elements, 0, resizeElementsArray, 0, elements.length);
            elements = resizeElementsArray;
        }
    }

    private void verifyIndex(int index) {
        if (index < 0 || size <= index) {
            throw new ArrayListIndexOutOfBoundsException("Index [" + index + "] is out of range");
        }
    }

    private void verifyIndexForAdd(int index) {
        if (index < 0 || size < index) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                + " is out of bounds for length " + size);
        }
    }
}
