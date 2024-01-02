package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private static final double GROWTH = 1.5;

    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkForIndexOutOfBounds(index);
        growIfArrayFull();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        size++;
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkForIndexOutOfBounds(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkForIndexOutOfBounds(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkForIndexOutOfBounds(index);
        T removed = (T) elementData[index];
        if (size - index == 1) {
            elementData[index] = null;
        } else {
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        }
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No element \"" + element + "\" in list!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size == elementData.length) {
            int newCapacity = (int) (elementData.length * GROWTH);
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
    }

    private void checkForIndexOutOfBounds(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }
}
