package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_COEFFICIENT = 2;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        growIfArrayFull();
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
        indexOutOfBounds(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexOutOfBounds(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexOutOfBounds(index);
        T result = elements[index];
        if (index != size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        elements[--size] = null;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size - 1; i++) {
            if ((elements[i] == element)
                    || (elements[i] != null && elements[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("no such element present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length
                    + elements.length / GROWTH_COEFFICIENT);
        }
    }

    private void indexOutOfBounds(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }
}
