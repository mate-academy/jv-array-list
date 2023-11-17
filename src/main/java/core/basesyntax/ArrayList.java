package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double MULTIPLICATION = 1.5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * MULTIPLICATION);
            copyArray(newCapacity);
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * MULTIPLICATION);
            copyArray(newCapacity);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = size + list.size();
        if (newSize > elements.length) {
            int newCapacity = (int) (elements.length * MULTIPLICATION);
            if (newCapacity < newSize) {
                newCapacity = newSize;
            }
            copyArray(newCapacity);
        }
        for (int i = 0; list.size() > i; i++) {
            elements[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) elements[index];
        int numMoved = size - index - 1;
        removeElement(index, numMoved);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? elements[i] == null : element.equals(elements[i])) {
                T removedElement = (T) elements[i];
                int numMoved = size - i - 1;
                removeElement(i, numMoved);
                return removedElement;
            }
        }
        throw new NoSuchElementException("Element not found in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    private void copyArray(int newCapacity) {
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void removeElement(int index, int numMoved) {
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
    }
}
