package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_LENGTH = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[INITIAL_LENGTH];
    }

    @Override
    public void add(T value) {
        if (size() == elements.length) {
            expandMainArray();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == elements.length) {
            expandMainArray();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
//        T[] tempArr1 = (T[]) new Object[index];
//        System.arraycopy(elements, 0, tempArr1, 0, index);
//        T[] tempArr2 = (T[]) new Object[size - index];
//        System.arraycopy(elements, index, tempArr2, 0, size - index);
//        elements = (T[]) new Object[++size];
//        elements[index] = value;
//        System.arraycopy(tempArr1, 0, elements, 0, index);
//        System.arraycopy(tempArr2, 0, elements, index + 1, size - index - 1);
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
        final T elementToReturn = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        --size;
        return elementToReturn;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element
                    || (elements[i] != null && elements[i].equals(element))) {
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

    private void expandMainArray() {
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
}
