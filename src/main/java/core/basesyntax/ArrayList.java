package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    private T[] elementsArray;
    private int size;

    public ArrayList() {
        elementsArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkIndexBounds(index, size + 1);
        if (size == elementsArray.length) {
            grow();
        }
        System.arraycopy(elementsArray, index, elementsArray, index + 1, size - index);
        elementsArray[index] = value;
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
        checkIndexBounds(index);
        return elementsArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBounds(index, size);
        elementsArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(index);
        T value = elementsArray[index];
        System.arraycopy(elementsArray, index + 1, elementsArray, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementsArray[i] || elementsArray[i] != null
                    && elementsArray[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element" + element + " is absent in the list.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int newCapacity = (int) (elementsArray.length * GROWTH_FACTOR);
        T[] newLimits = (T[]) new Object[newCapacity];
        System.arraycopy(elementsArray, 0, newLimits, 0, size);
        elementsArray = newLimits;
    }

    private void checkIndexBounds(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    private void checkIndexBounds(int index) {
        checkIndexBounds(index, size);
    }
}
