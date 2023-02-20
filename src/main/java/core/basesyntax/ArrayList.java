package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        fullArray();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        fullArray();
        if (index > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
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
        checkIndexRange(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexRange(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexRange(index);
        T elementToRemove = elements[index];
        System.arraycopy(elements, index + 1, elements, index, (size - index) - 1);
        elements[--size] = null;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (equalElement(elements[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void oldArrayCopy() {
        int nextLength = elements.length + (elements.length >> 1);
        T[] resizedElements = (T[]) new Object[nextLength];
        System.arraycopy(elements, 0, resizedElements, 0, elements.length);
        elements = resizedElements;
    }

    private void checkIndexRange(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    private boolean equalElement(T elementFromArray, T element) {
        return elementFromArray == element
                || elementFromArray != null && (elementFromArray.equals(element));
    }

    private void fullArray() {
        if (size() == elements.length) {
            oldArrayCopy();
        }
    }
}
