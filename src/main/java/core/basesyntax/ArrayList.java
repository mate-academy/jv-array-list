package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int arraySize = 0;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elementData[arraySize] = (T) value;
        arraySize++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        grow();
        System.arraycopy(elementData, index, elementData, index + 1, arraySize - index);
        elementData[index] = (T) value;
        arraySize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        getException(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        getException(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        getException(index);
        T removedElement = elementData[index];
        if (index != elementData.length - 1) {
            System.arraycopy(elementData, index + 1, elementData,
                    index, arraySize - 1);
        } else {
            elementData[index] = null;
        }
        arraySize--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (element == null && elementData[i] == null || elementData[i] != null
                        && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element");
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    private void grow() {
        if (elementData.length == arraySize) {
            Object newArray = new Object[(int)(size() * 1.5)];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = (T[])newArray;
        }
    }

    public void getException(int index) {
        if (index < 0 || index >= arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }
}


