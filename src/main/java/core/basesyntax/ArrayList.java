package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index + " or index is "
                    + "greater than size " + size);
        }
        resizeIfNeeded();
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
        checkIndexIsInBounds(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexIsInBounds(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexIsInBounds(index);
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null && element.equals(elements[i])
                    || (element == null && elements[i] == null)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not exist in array: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeIfNeeded() {
        if (elements.length == size) {
            T[] newArray = (T[]) new Object[(int) (elements.length * GROW_FACTOR)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = (T[]) newArray;
        }
    }

    private void checkIndexIsInBounds(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index + " or index is "
                    + "smaller than zero " + size);
        }
    }
}
