package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double LOAD_FACTOR = 1.5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size + 1);
        resizeIfNeeded();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] newArray = new Object[list.size()];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = list.get(i);
        }
        resizeIfNeeded();
        System.arraycopy(newArray, 0, elements, size, newArray.length);
        size = size + newArray.length;
    }

    @Override
    public T get(int index) {
        checkIndex(index, size);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        T removedElemen = null;
        checkIndex(index, size);
        for (int i = 0; i < size; i++) {
            if (i == index) {
                removedElemen = (T) elements[i];
            }
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElemen;
    }

    @Override
    public T remove(T element) {
        T removedElement = null;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                index = i;
                removedElement = (T) elements[i];
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("No such element was found");
        } else {
            System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
            size--;
        }
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds: ");
        }
    }

    private void resizeIfNeeded() {
        if (size >= elements.length - 2) {
            Object[] newArray = new Object[(int) (elements.length + elements.length * LOAD_FACTOR)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }
}
