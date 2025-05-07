package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double MULTIPLIER_TO_ARRAY_GROW = 1.5;
    private Object[] elements;
    private int size = 0;

    public ArrayList() {
        elements = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        resize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size);
        resize();
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
        checkIndex(index, size - 1);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size - 1);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size - 1);
        T removedValue = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || element != null && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element is not valid: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (size == elements.length) {
            int newSize = (int) (elements.length * MULTIPLIER_TO_ARRAY_GROW);
            Object[] expandedArray = new Object[newSize];
            System.arraycopy(elements, 0, expandedArray, 0, elements.length);
            elements = expandedArray;
        }
    }

    private void checkIndex(int index, int upperBound) {
        if (index < 0 || index > upperBound) {
            throw new ArrayListIndexOutOfBoundsException("Index not valid: " + index
                    + ", upper bound: " + upperBound);
        }
    }
}
