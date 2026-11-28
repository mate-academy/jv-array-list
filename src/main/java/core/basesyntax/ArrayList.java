package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_DIVISION = 2;

    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkPositionIndex(index);
        ensureCapacity();

        System.arraycopy(elements, index, elements, index + 1, size - index);

        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int initialListSize = list.size();

        for (int index = 0; index < initialListSize; index++) {
            add(list.get(index));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkElementIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkElementIndex(index);
        elements[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkElementIndex(index);

        final T removeElement = (T) elements[index];

        for (int currentIndex = index; currentIndex < size - 1; currentIndex++) {
            elements[currentIndex] = elements[currentIndex + 1];
        }

        size--;
        elements[size] = null;

        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < size; index++) {
            if (elements[index] == null ? element == null : elements[index].equals(element)) {
                return remove(index);
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
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length + elements.length / GROWTH_DIVISION;
            Object[] newElements = new Object[newCapacity];

            for (int index = 0; index < size; index++) {
                newElements[index] = elements[index];
            }

            elements = newElements;
        }
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index: " + index + ", Size: " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}

