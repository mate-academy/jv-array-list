package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private T[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        validateIndex(index, true);
        ensureCapacity();
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
        validateIndex(index, false);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index, false);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index, false);
        T oldValue = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (equals(elements[i], element)) {
                return remove(i);
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
            resize();
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = (int) (elements.length * GROW_FACTOR);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    private void validateIndex(int index, boolean isAddOperation) {
        if (index < 0 || index > size || (!isAddOperation && index == size)) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Invalid index: " + index + ", Size: " + size);
        }
    }

    private boolean equals(T a, T b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
