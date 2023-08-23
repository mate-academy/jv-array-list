package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_MULTIPLIER = 1.5;
    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            growArray();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }
        if (size == elements.length) {
            growArray();
        }
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
        checkOutOfBounds(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkOutOfBounds(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkOutOfBounds(index);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(element)
                    || elements[i] == element) {
                T removedElement = (T) elements[i];
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                size--;
                return removedElement;
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkOutOfBounds(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect: " + index);
        }
    }

    private void growArray() {
        int newSize = (int) Math.round(elements.length * GROW_MULTIPLIER);
        Object[] newElements = new Object[newSize];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}
