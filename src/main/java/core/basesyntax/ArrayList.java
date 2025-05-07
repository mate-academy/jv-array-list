package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_INDEX = 1.5;
    private static final int NOT_FOUND = -1;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow(size + 1);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
        if (size == elementData.length) {
            grow(size + 1);
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int numberToAdd = list.size();
        if (numberToAdd > elementData.length - size) {
            grow(size + numberToAdd);
        }
        for (int i = 0; i < numberToAdd; i++) {
            elementData[size + i] = list.get(i);
        }
        size += numberToAdd;
    }

    @Override
    public T get(int index) {
        checkIfIndexValid(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexValid(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexValid(index);
        T elementToRemove = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        int indexToRemove = NOT_FOUND;
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                indexToRemove = i;
            }
        }
        if (indexToRemove == NOT_FOUND) {
            throw new NoSuchElementException("No such element " + element);
        }
        return remove(indexToRemove);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow(int minCapacity) {
        int newCapacity = (int) (elementData.length * CAPACITY_INDEX);
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, elementData.length);
        elementData = newArray;
    }

    private void checkIfIndexValid(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }
}
