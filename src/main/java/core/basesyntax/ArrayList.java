package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int capacity;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        size = 0;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index > size + 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index passed is invalid");
        }
        if (index == capacity || size == capacity - 1) {
            elementData = grow();
        }
        if (index < size) {
            for (int i = size; i >= index; i--) {
                elementData[i + 1] = elementData[i];
            }
        }
        elementData[index] = value;
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
        verifyIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        verifyIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        verifyIndex(index);
        T removedElement = elementData[index];
        System.arraycopy(elementData,index + 1,elementData, index,size - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i <= size; i++) {
            if (elementData[i] == element || elementData[i] != null && elementData[i].equals(element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i <= size; i++) {
            if (elementData[i] != null) {
                return false;
            }
        }
        return true;
    }

    private int increaseSize() {
        capacity = capacity + (DEFAULT_CAPACITY >> 1);
        return capacity;
    }

    private T[] grow() {
        T[] newArray = (T[]) new Object[increaseSize()];
        System.arraycopy(elementData,0,newArray, 0,size);
        return newArray;
    }

    private void verifyIndex(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index passed is invalid");
        }
    }
}
