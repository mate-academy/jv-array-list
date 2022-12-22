package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_COEFFICIENT = 1.5;
    private static final int FIRST_ELEMENT = 0;
    private int size;
    private T[] elementsData;

    public ArrayList() {
        this.elementsData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void grow() {
        T[] newElementsData = elementsData;
        elementsData = (T[]) new Object[(int) (elementsData.length * GROWTH_COEFFICIENT)];
        System.arraycopy(newElementsData, FIRST_ELEMENT, elementsData, FIRST_ELEMENT,
                newElementsData.length);
    }

    public boolean findIndexOfList(int index) {
        if (index >= size || index < FIRST_ELEMENT) {
            throw new ArrayListIndexOutOfBoundsException("Invalid Index " + index);
        }
        return true;
    }

    @Override
    public void add(T value) {
        if (size >= elementsData.length) {
            grow();
        }
        elementsData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size || findIndexOfList(index)) {
            if (size >= elementsData.length) {
                grow();
            }
            System.arraycopy(elementsData, index, elementsData, index + 1, size - index);
            elementsData[index] = value;
            size++;
        }
    }

    public void deleteElementOfList(int index) {
        if (index == elementsData.length - 1) {
            elementsData[index - 1] = null;
        }
        System.arraycopy(elementsData, index + 1, elementsData, index, size - index - 1);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (findIndexOfList(index)) {
            return elementsData[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (findIndexOfList(index)) {
            elementsData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (findIndexOfList(index)) {
            T deleted = elementsData[index];
            deleteElementOfList(index);
            size = size - 1;
            return deleted;
        }
        return null;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elementsData[i] == t || elementsData[i] != null && elementsData[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found " + t);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
