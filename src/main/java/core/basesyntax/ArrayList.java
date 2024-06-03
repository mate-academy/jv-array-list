package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length
                    + (elements.length >> 1);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private void checkIndexBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index
                            + " out of bounds for length " + size
            );
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index
                            + " out of bounds for length "
                            + size
            );
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index
                + 1, size - index);
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
        checkIndexBounds(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBounds(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(index);
        T removedElement = (T) elements[index];
        int numToMove = size - index - 1;
        if (numToMove > 0) {
            System.arraycopy(elements, index + 1, elements, index, numToMove);
        }
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? elements[i] == null
                    : element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(
                "Element '" + element
                        + "' not found in the list"
        );
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
