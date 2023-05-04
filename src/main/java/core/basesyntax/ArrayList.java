package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int EMPTY_SIZE = 0;
    private static final double ARRAY_MULTIPLIER = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index  " + index + " does not exist");
        }
        checkCapacity();
        System.arraycopy(elements, index, elements, index + 1, size++ - index);
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
        final T element = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size-- - index - 1);
        elements[size] = null;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i]
                    || (element != null && element.equals(elements[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("The element " + element + " is not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY_SIZE;
    }

    private void checkCapacity() {
        if (elements.length == size) {
            T[] biggerArray = (T[]) new Object[(int) (elements.length * ARRAY_MULTIPLIER)];
            System.arraycopy(elements, 0, biggerArray, 0, size);
            elements = biggerArray;
        }
    }

    private void checkIndexBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(index + " out of bounds");
        }
    }
}
