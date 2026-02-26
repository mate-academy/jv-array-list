package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final float GROWTH_FACTOR = 1.5F;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(
                    "Illegal Capacity: " + capacity);
        }
        elements = (T[]) new Object[capacity];
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
                    String.format("Index %d is out of bounds for ArrayList with size %d",
                            index, size));
        }

        ensureCapacity();

        System.arraycopy(elements, index,
                elements, index + 1,
                size - index);

        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("List cannot be null");
        }

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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d is out of bounds for ArrayList with size %d",
                            index, size));
        }

        final T removedElement = elements[index];

        System.arraycopy(elements, index + 1,
                elements, index,
                size - index - 1);

        elements[size - 1] = null;
        size--;

        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elements[i] == null) {
                    return remove(i);
                }
            } else {
                if (element.equals(elements[i])) {
                    return remove(i);
                }
            }
        }

        throw new NoSuchElementException(
                "Element " + element + " is not found in the ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            resize();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d is out of bounds for ArrayList with size %d",
                            index, size));
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d is out of bounds for ArrayList with size %d",
                            index, size));
        }
    }

    private void resize() {
        int newCapacity = (int) (elements.length * GROWTH_FACTOR);
        T[] newElements = (T[]) new Object[newCapacity];

        System.arraycopy(elements, 0,
                newElements, 0,
                elements.length);

        elements = newElements;
    }
}
