package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int EXPANSION_FACTOR = 2;
    private static final int OFFSET = 1;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growArrayIfRequred();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index + size);
        }
        growArrayIfRequred();
        System.arraycopy(elements, index, elements, index + OFFSET, size - index);
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
        checkIndexBounds(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBounds(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(index);
        T removedElement = elements[index];
        System.arraycopy(elements, index + OFFSET, elements, index, size - index - OFFSET);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = -OFFSET;
        for (int i = 0; i < size; i++) {
            if (element == null && elements[i] == null
                    || element != null && element.equals(elements[i])) {
                index = i;
                break;
            }
        }
        if (index != -OFFSET) {
            return remove(index);
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

    private void checkIndexBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index + size);
        }
    }

    private void growArrayIfRequred() {
        if (size >= elements.length) {
            T[] newElements = (T[]) new Object[elements.length * EXPANSION_FACTOR];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }
}
