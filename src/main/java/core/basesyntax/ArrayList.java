package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_INDEX = 1.5;
    private T[] elements;
    private int size;

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
        checkIndex(index);
        resize();
        System.arraycopy(elements, index, elements, index + 1, elements.length - index - 1);
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
        checkIfIndexFits(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexFits(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexFits(index);
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int removedIndex = -1;
        T value = null;
        for (int i = 0; i < size; i++) {
            if ((elements[i] == element) || (elements[i] != null && elements[i].equals(element))) {
                value = elements[i];
                this.remove(i);
                return value;
            }
        }
        throw new NoSuchElementException("There is no such element in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (elements.length == size) {
            int newCapacity = (int) (elements.length * CAPACITY_INDEX);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = (T[]) newArray;
        }
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }
    }

    private void checkIfIndexFits(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("There is no such index in the list, "
                    + index);
        }
    }
}
