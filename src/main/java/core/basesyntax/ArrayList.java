package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private static final double COEFFICIENT_OF_EXPANSION = 1.5;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[INITIAL_SIZE];
    }

    @Override
    public void add(T value) {
        expand();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        expand();
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
        final T removed = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
        elements[size() - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (elements[i] != null && elements[i].equals(element) || elements[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found in list " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size && index != 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index
                    + " for size " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size() + 1) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index
                    + " for size " + size);
        }
    }

    private void expand() {
        if (size >= elements.length) {
            T[] expandedElements = (T[]) new Object[(int) (elements.length
                    * COEFFICIENT_OF_EXPANSION)];
            System.arraycopy(elements, 0, expandedElements, 0, size);
            elements = expandedElements;
        }
    }
}
