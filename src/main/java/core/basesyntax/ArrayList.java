package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    @Override
    public void add(T value) {
        if (elements == null) {
            elements = (T[]) new Object[DEFAULT_CAPACITY];
        }
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length + elements.length / 2);
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        if (elements == null) {
            elements = (T[]) new Object[DEFAULT_CAPACITY];
        }
        if (size + 1 == elements.length) {
            elements = Arrays.copyOf(elements, elements.length + elements.length / 2);
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
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        } else {
            return elements[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        T result = elements[index];
        if (index != size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        elements[--size] = null;
        return result;
    }

    @Override
    public T remove(T element) {
        int sizeBefore = size;
        for (int i = 0; i < size - 1; i++) {
            if ((elements[i] == null && element == null)
                    || (elements[i] != null && elements[i].equals(element))) {
                remove(i);
            }
        }
        if (sizeBefore == size) {
            throw new NoSuchElementException("no such element present");
        }
        return element;
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
