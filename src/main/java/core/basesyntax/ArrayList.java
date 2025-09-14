package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    private T[] elements;
    private int size;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
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
        T removed = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null; // prevent memory leak
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == null && element == null)
                    || (elements[i] != null && elements[i].equals(element))) {
                T removed = elements[i];
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[--size] = null;
                return removed;
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

    /** ✅ Missing clear() implementation */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null; // allow GC to clean up
        }
        size = 0;
    }

    // ----------- private helpers -------------
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
}
