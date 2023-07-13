package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_COEFFICIENT = 1.5;
    private static final double EMPTY_SIZE = 0;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        growIfArrayFull();
        checkBoundForAdding(index);
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
        checkBounds(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkBounds(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBounds(index);
        T temp = elements[index];
        size--;
        System.arraycopy(elements, index + 1, elements, index, size - index);
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(element) || elements[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no " + element + " in list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY_SIZE;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

    private void checkBoundForAdding(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

    private void growIfArrayFull() {
        if (size == elements.length) {
            grow();
        }
    }

    private void grow() {
        int newSize = (int) (elements.length * INCREASE_COEFFICIENT);
        T[] oldArray = elements;
        elements = (T[]) new Object[newSize];
        System.arraycopy(oldArray, 0, elements, 0, size);
    }
}
