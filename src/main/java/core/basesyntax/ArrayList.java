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
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else if (indexCheck(index)) {
            checkToGrow();
            T[] newElementData = (T[]) new Object[elementData.length];
            System.arraycopy(elementData, 0, newElementData, 0, index);
            newElementData[index] = value;
            System.arraycopy(elementData, index, newElementData, index + 1, size - index);
            elementData = newElementData;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index Out of Bounds");
        }

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

    private void grow(int listSize) {
        T[] newElementData = (T[]) new Object[size + listSize];
        System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
        elementData = newElementData;
    }

    @Override
    public void addAll(List<T> list) {
        grow(list.size());
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i), size);
        }
    }

    @Override
    public T get(int index) {
        if (index <= size - 1 && index >= 0) {
            return (T) elementData[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index Out Of Bounds");
    }

    @Override
    public void set(T value, int index) {
        if (indexCheck(index)) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is negative or out of bounds");
        }
    }

    private boolean indexCheck(int index) {
        return index < size && index >= 0;
    }

    @Override
    public T remove(int index) {
        if (!indexCheck(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index is negative or out of bounds");
        } else {
            T[] newElementData = (T[]) new Object[elementData.length];
            System.arraycopy(elementData, 0, newElementData, 0, index);
            System.arraycopy(elementData, index + 1, newElementData, index, size - index - 1);
            T valueToRemove = (T) elementData[index];
            elementData = newElementData;
            size--;
            return valueToRemove;
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if ((elementData[i] == null && element == null)
                    || (elementData[i] != null && elementData[i].equals(element))) {
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
