package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROWTH_FACTOR = 1.5;
    private static final int CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        size = 0;
        elements = (T[]) new Object[CAPACITY];
    }

    public void resize() {
        int newCapacity = (int) (elements.length * GROWTH_FACTOR);
        T[] newArray = (T[]) new Object[newCapacity ];

        System.arraycopy(elements, 0, newArray, 0, size);

        elements = newArray;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index);
        }
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            resize();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index);
        }

        if (size == elements.length) {
            resize();
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);

        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (size == elements.length) {
                resize();
            }
            elements[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);

        final T removed = elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);

        elements[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == null && element == null)
                    || (elements[i] != null && elements[i].equals(element))) {
                final T removed = elements[i];

                System.arraycopy(elements, i + 1, elements, i, size - i - 1);

                elements[size - 1] = null;
                size--;
                return removed;
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
        return (size == 0);
    }
}
