package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_RATE = 1.5;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;

    private T[] elements;
    private int size;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
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
        /*  exception 'Foreach not applicable to type 'core.basesyntax.List<T>'
        how can we solve this?

        for (T element : list) {
            add(element);
        }
        */
    }

    @Override
    public T get(int index) {
        checkIndexBelowZeroOrSizeEquals(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBelowZeroOrSizeEquals(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBelowZeroOrSizeEquals(index);
        final T removedElement = get(index); //final because of checkstyle
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[size - 1] = null; // can we use prefix decrement here? elements[--size] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || elements[i] != null && elements[i].equals(element)) {
                remove(i);
                return element;
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

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = (int) Math.min(MAX_ARRAY_SIZE, elements.length * GROWTH_RATE);
            resizeArray(newCapacity);
        }
    }

    private void resizeArray(int newCapacity) {
        T[] newElements = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void checkIndexBelowZeroOrSizeEquals(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
