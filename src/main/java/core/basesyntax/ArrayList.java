package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, true);
        resize();
        if (size - index >= 0) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resize();
            elements[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, false);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, false);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);
        T oldElement = elements[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
        }
        elements[--size] = null;
        return oldElement;
    }

    @Override
    public T remove(T element) {
        int index = index(element);
        if (index == -1) {
            throw new NoSuchElementException("Element " + element + " not found");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index, boolean inclusive) {
        if (index < 0 || (inclusive ? index > size : index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("Index is " + index
                    + ", Size is " + size + ".");
        }
    }

    private boolean elementsAreEqual(T element1, T element2) {
        return (element1 == null && element2 == null)
                || (element1 != null && element1.equals(element2));
    }

    private void resize() {
        if (elements.length == size) {
            int newCapacity = (int) (elements.length * GROWTH_FACTOR);
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private int index(T element) {
        for (int i = 0; i < size; i++) {
            if (elementsAreEqual(elements[i], element)) {
                return i;
            }
        }
        return -1;
    }
}
