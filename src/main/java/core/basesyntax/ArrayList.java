package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int SIZE = 10;
    private static final double MULTIPLIER = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[SIZE];
    }

    @Override
    public void add(T value) {
        if (elements.length == size) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || size < index) {
            throw new ArrayListIndexOutOfBoundsException("No valid index: " + index);
        }
        if (elements.length == size) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
    }

    @Override
    public void addAll(List<T> list) {
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
        checkIndex(index);
        final T removedValue = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        elements[size] = null;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (Objects.equals(element, elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No valid element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int newCapacity = (int) (elements.length * MULTIPLIER);
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = (T[]) newElements;
    }

    private void checkIndex(int index) {
        if (index < 0 || size <= index) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index);
        }
    }
}

