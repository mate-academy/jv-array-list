package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ADD_ONE_ELEMENT = 1;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[])new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size >= elements.length) {
            resizeIfNeeded(ADD_ONE_ELEMENT);
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        verifyIndexForAdd(index);
        resizeIfNeeded(ADD_ONE_ELEMENT);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        resizeIfNeeded(list.size());
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
        T deletedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] != null && elements[i].equals(element)) || elements[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeIfNeeded(int extraSize) {
        if (size + extraSize > elements.length) {
            int newSize = elements.length + (elements.length >> 1);
            T[] newArray = (T[]) new Object[newSize];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void verifyIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("invalid index: " + index + " not exist");
        }
    }

    private void verifyIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("wrong index: " + index
                    + " for ArrayList size: " + size);
        }
    }
}
