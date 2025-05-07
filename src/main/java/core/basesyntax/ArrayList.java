package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[INITIAL_SIZE];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexBeforeAddition(index);
        if (size == elements.length) {
            grow();
        }
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
        T removedValue = elements[index];
        int newLength = size - index - 1;
        System.arraycopy(elements, index + 1, elements, index, newLength);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || element != null && element.equals(elements[i])) {
                T removed = elements[i];
                System.arraycopy(elements, i + 1, elements, i, size - i);
                size--;
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

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void checkIndexBeforeAddition(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void grow() {
        T[] newElements = (T[]) new Object[elements.length + (elements.length >> 1)];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}
