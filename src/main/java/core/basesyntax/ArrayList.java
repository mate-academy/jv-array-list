package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elements;

    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("the size of the array cannot be below zero");
        }
        elements = (T[]) new Object[initialCapacity];
    }

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        capacityCheck();
        if (size < elements.length) {
            elements[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of range " + size);
        }
        capacityCheck();
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of range " + size);
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexExist(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexExist(index);
        T element = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (element != null && element.equals(elements[i]) || element == elements[i]) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't find element!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    private void isIndexExist(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of range " + size);
        }
    }

    private void capacityCheck() {
        if (size == elements.length) {
            T[] temp = (T[]) new Object[elements.length + (int)(elements.length * 1.5)];
            System.arraycopy(elements, 0, temp, 0, size);
            elements = temp;
        }
    }
}
