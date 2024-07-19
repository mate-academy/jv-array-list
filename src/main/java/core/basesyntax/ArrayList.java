package core.basesyntax;

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
        checkCapacity(size + 1);
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, true);
        checkCapacity(size + 1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        checkCapacity(size + list.size());
        for (int i = 0; i < list.size(); i++) {
            elements[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, false);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, false);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);
        T oldElement = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return oldElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementsAreEqual(element, elements[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Element " + element + " not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index, boolean inclusive) {
        if (index < 0 || (inclusive ? index > size : index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("Index is " + index
                    + ", Size is " + size + ".");
        }
    }
    
    private void checkCapacity(int capacity) {
        if (capacity > elements.length) {
            int newCapacity = (int) (elements.length * GROWTH_FACTOR);
            if (newCapacity < capacity) {
                newCapacity = capacity;
            }
            grow(newCapacity);
        }
    }

    private void grow(int newCapacity) {
        T[] newElements = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private boolean elementsAreEqual(T element1, T element2) {
        return (element1 == null && element2 == null)
                || (element1 != null && element1.equals(element2));
    }
}
