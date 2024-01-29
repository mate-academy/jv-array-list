package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ITEMS_NUMBER = 10;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_ITEMS_NUMBER];
    }

    @Override
    public void add(T value) {
        checkToGrow();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (!indexCheck(index) && index != size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is negative or out of bounds with size: " + size);
        } else if (index == size) {
            add(value);
        } else {
            checkToGrow();
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i), size);
        }
    }

    @Override
    public T get(int index) {
        outOfBoundsCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        outOfBoundsCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        outOfBoundsCheck(index);
        T valueToRemove = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return valueToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if ((elementData[i] == element)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkToGrow() {
        if (size == elementData.length) {
            grow();
        }
    }

    private void grow() {
        T[] newElementData = (T[]) new Object[elementData.length + (elementData.length >> 1)];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    private void outOfBoundsCheck(int index) {
        if (!indexCheck(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is negative or out of bounds with size: " + size);
        }
    }

    private boolean indexCheck(int index) {
        return index < size && index >= 0;
    }
}
