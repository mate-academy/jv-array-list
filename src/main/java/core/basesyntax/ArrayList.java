package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    private Object[] elements;
    private int size = 0;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();

        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkPositionIndex(index);
        grow();

        System.arraycopy(elements, index, elements, index + 1, size - index);

        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int orginalSize = list.size();
        for (int i = 0; i < orginalSize; i++) {
            add(list.get(i));

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

        final Object savedValue = elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);

        size--;
        elements[size] = null;

        return (T) savedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? elements[i] == null : element.equals(elements[i])) {
                final Object savedValue = elements[i];

                System.arraycopy(elements, i + 1, elements, i, size - i - 1);

                size--;
                elements[size] = null;

                return (T) savedValue;
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

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void grow() {
        if (size == elements.length) {
            Object[] newElements =
                    new Object[(int) (elements.length * GROWTH_FACTOR)];

            System.arraycopy(elements, 0, newElements, 0, size);

            elements = newElements;
        }
    }
}
