package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(int index, T value) {
        checkIndexForAdd(index);
        ensureCapacity();

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = value;
        size++;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public T set(int index, T value) {
        checkIndex(index);

        T oldValue = elements[index];
        elements[index] = value;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return removedValue;
    }

    @Override
    public boolean remove(T value) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(value)) {
                remove(i);
                return true;
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * GROWTH_FACTOR);
            elements = java.util.Arrays.copyOf(elements, newCapacity);
        }
    }
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }
}
