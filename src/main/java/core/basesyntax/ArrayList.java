package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
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
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    T removedElement = elements[i];
                    System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                    elements[--size] = null;
                    return removedElement;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    T removedElement = elements[i];
                    System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                    elements[--size] = null;
                    return removedElement;
                }
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

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = (int) Math.max(elements.length * GROWTH_FACTOR, minCapacity);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private void checkIndex(int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Input index: " + index + " out of bound: "
                    + size);
        }
    }

    private void checkIndexForAdd(int index) throws ArrayListIndexOutOfBoundsException {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Input index: "
                    + index + " out of bound: " + size);
        }
    }
}
