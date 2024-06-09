package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double CAPACITY_MULTIPLIER = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("invalid index entered, index"
                    + " cannot be less than zero or greater than the size of the array");
        }
        growIfArrayFull();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            growIfArrayFull();
            elementData[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkingIndexIsValid(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkingIndexIsValid(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkingIndexIsValid(index);
        growIfArrayFull();
        T result = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null ? element == null : elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("no such element present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0) ? true : false;
    }

    private void growIfArrayFull() {
        if (size == elementData.length) {
            int newCapacity = (int) (elementData.length * CAPACITY_MULTIPLIER);
            T[] newElementData = (T[]) new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
            elementData = newElementData;
        }
    }

    private void checkingIndexIsValid(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("invalid index entered, index"
                    + " cannot be less than zero or greater than the size of the array");
        }
    }
}
