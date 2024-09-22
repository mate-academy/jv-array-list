package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASING_ARRAY = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void growArray() {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * INCREASING_ARRAY);
            T[] newElements = (T[]) new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }

    private void validateIndex(int index, boolean forAdd) {
        if (index < 0 || index >= (forAdd ? size + 1 : size)) {
            throw new ArrayListIndexOutOfBoundsException("Index:  " + index + " Size: " + size);
        }
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            growArray();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        validateIndex(index, true);
        if (size == elements.length) {
            growArray();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        size++;
        elements[index] = value;
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
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && elements[i] == null) {
                return remove(i);
            }
            if (element != null && element.equals(elements[i])) {
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
}
