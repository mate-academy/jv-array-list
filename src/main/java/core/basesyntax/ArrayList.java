package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_INDEX = 1.5;
    private int size = 0;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == elements.length) {
            grow();
        }
        if (index >= 0 && index <= size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return (T) elements[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            elements[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T removedElement = (T) elements[index];
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
            size--;
            return removedElement;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == null && element == null)
                    || (elements[i] != null && elements[i].equals(element))) {
                T removedElement = (T) elements[i];
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                size--;
                return removedElement;
            }
        }
        throw new NoSuchElementException("Element not found");
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
        int newSize = (int) Math.round(elements.length * GROW_INDEX);
        Object[] newElements = new Object[newSize];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}
