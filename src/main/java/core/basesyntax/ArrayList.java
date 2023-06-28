package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final byte INITIAL_CAPACITY = 10;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE;
    private static final double CAPACITY_MULTIPLIER = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            increaseCapacity();
        }

        insertElementAt(size, value);
    }

    @Override
    public void add(T value, int index) {
        checkIndexBetweenBounds(index);

        if (size == elementData.length) {
            increaseCapacity();
        }

        if (index < size) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
        }

        insertElementAt(index, value);
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new RuntimeException("List must not be a null");
        }

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexWithinBounds(index);

        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexWithinBounds(index);

        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexWithinBounds(index);

        return removeElementAt(index);
    }

    @Override
    public T remove(T element) {
        int index = getElementIndex(element);

        if (index == -1) {
            throw new NoSuchElementException("Unable to find provided element in the list");
        }

        return removeElementAt(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getElementIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (
                    elementData[i] == element
                    || (elementData[i] != null && elementData[i].equals(element))
            ) {
                return i;
            }
        }

        return -1;
    }

    private void checkIndexBetweenBounds(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

    private void checkIndexWithinBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

    private void insertElementAt(int index, T value) {
        elementData[index] = value;
        size++;
    }

    private T removeElementAt(int index) {
        T removedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removedElement;
    }

    private void increaseCapacity() {
        T[] newElementData = (T[]) new Object[newCapacity()];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    private int newCapacity() {
        long newCapacity = (long) (elementData.length * CAPACITY_MULTIPLIER);

        if (newCapacity > MAX_CAPACITY) {
            throw new RuntimeException("ArrayList capacity overflow");
        }

        return (int) newCapacity;
    }
}
