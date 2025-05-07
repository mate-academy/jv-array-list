package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int HALF_DIVIDER_INDEX = 2;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of range: Size is: " + size);
        }
        resize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resize();
            elements[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkBoundInclusive(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkBoundInclusive(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBoundInclusive(index);
        final T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = findIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("No such element found " + element);
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int findIndex(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i] == element
                    || elements[i] != null && elements[i].equals(element)) {
                index = i;
            }
        }
        return index;
    }

    private void resize() {
        if (elements.length == size) {
            int newCapacity = (elements.length * HALF_DIVIDER_INDEX);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = (T[]) newArray;
        }
    }

    private void checkBoundInclusive(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of range: Size is: " + size);
        }
    }
}
