package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_COEFFICIENT = 1.5;
    private static final int INDEX_FIRST_ELEMENT = 0;
    private int size;
    private T[] elements;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= elements.length) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size || isValidIndex(index)) {
            if (size >= elements.length) {
                grow();
            }
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (isValidIndex(index)) {
            return elements[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (isValidIndex(index)) {
            elements[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (isValidIndex(index)) {
            T deleted = elements[index];
            deleteElement(index);
            size = size - 1;
            return deleted;
        }
        return null;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isValidIndex(int index) {
        if (index >= size || index < INDEX_FIRST_ELEMENT) {
            throw new ArrayListIndexOutOfBoundsException("Invalid Index " + index);
        }
        return true;
    }

    private void grow() {
        T[] newElements = elements;
        elements = (T[]) new Object[(int) (elements.length * GROWTH_COEFFICIENT)];
        System.arraycopy(newElements, INDEX_FIRST_ELEMENT, elements, INDEX_FIRST_ELEMENT,
                newElements.length);
    }

    private void deleteElement(int index) {
        if (index == elements.length - 1) {
            elements[index - 1] = null;
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
    }
}
