package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INIT_SIZE = 10;
    private static final double RESIZE_LIST = 1.5;
    private static final int FIRST_ELEMENT = 0;
    private int size;
    private T[] elementsData = (T[]) new Object[INIT_SIZE];

    private void grow() {
        T[] newElementsData = elementsData;
        elementsData = (T[]) new Object[(int) (elementsData.length * RESIZE_LIST)];
        System.arraycopy(newElementsData, FIRST_ELEMENT, elementsData, FIRST_ELEMENT,
                newElementsData.length);
    }

    public boolean findIndexOfList(int index) {
        if (index >= size || index < FIRST_ELEMENT) {
            throw new ArrayListIndexOutOfBoundsException("Index not found");
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
            return;
        }
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
        throw new NoSuchElementException("No such element");
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

