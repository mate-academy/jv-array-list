package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    private T[] elements;
    private int size = 0;

    @SuppressWarnings("unchecked")
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
        checkPositionIndex(index);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int toAdd = list.size();
        if (toAdd == 0) {
            return;
        }
        ensureCapacity(size + toAdd);
        for (int i = 0; i < toAdd; i++) {
            elements[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkElementIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        return fastRemove(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i]
                    || element != null && element.equals(elements[i])) {
                return fastRemove(i);
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

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int minCapacity) {
        if (elements.length < minCapacity) {
            int newCapacity = Math.max((int) (elements.length * GROWTH_FACTOR), minCapacity);
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " out of bounds for size " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " out of bounds for size " + size);
        }
    }

    private T fastRemove(int index) {
        T removed = elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return removed;
    }
}
