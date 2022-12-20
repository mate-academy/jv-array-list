package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_COEFFICIENT = 1.5;
    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkSize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkSize();
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("" + index);
        }
        size++;
        System.arraycopy(elements, index, elements, index + 1, size - index);
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
        grow(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        grow(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        grow(index);
        T value = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && elements[i] == null
                    || (elements[i] != null && elements[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkSize() {
        if (size >= elements.length - 1) {
            Object[] currentArray = new Object[(int) (elements.length * GROWTH_COEFFICIENT)];
            System.arraycopy(elements, 0, currentArray, 0, size);
            elements = currentArray;
        }
    }

    private void grow(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index not found " + index);
        }
    }
}
