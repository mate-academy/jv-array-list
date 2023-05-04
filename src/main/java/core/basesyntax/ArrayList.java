package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_LENGTH = 10;
    private static final double GROW_FACTOR = 1.5;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
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
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null && elements[i].equals(element) || elements[i] == element) {
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

    private void resizeIfNeeded() {
        if (elements.length == size) {
            Object[] newArray = new Object[(int)(elements.length * GROW_FACTOR)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = (T[]) newArray;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Input index " + index
                    + "out of bound " + size);
        }
    }

    private void checkAddIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Input index " + index
                        + "out of bound " + size);
        }
    }
}
