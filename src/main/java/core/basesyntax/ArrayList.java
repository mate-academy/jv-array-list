package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int numberOfElements;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        numberOfElements = 0;
    }

    public T[] grow() {
        T[] arrayT = (T[]) new Object[elementData.length * 2];
        System.arraycopy(elementData, 0, arrayT, 0, numberOfElements);
        return arrayT;

    }

    public boolean checkIndex(int index) {
        if (index < 0 || index >= numberOfElements) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return true;
        }
    }

    @Override
    public void add(T value) {
        if (numberOfElements == elementData.length) {
            elementData = grow();
        }
        elementData[numberOfElements++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == numberOfElements) {
            add(value);
            return;
        }
        checkIndex(index);
        if (numberOfElements == elementData.length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1,
                numberOfElements - index);
        elementData[index] = value;
        numberOfElements++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }

    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T value = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData,
                index, numberOfElements - (index + 1));
        numberOfElements--;
        return value;
    }

    @Override
    public T remove(T t) {
        int index = -1;
        for (int i = 0; i < numberOfElements; i++) {
            if (elementData[i] == t
                    || elementData[i] != null && elementData[i].equals(t)) {
                index = i;
            }
        }
        if (index != -1) {
            T value = (T) elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index,
                    numberOfElements - (index + 1));
            numberOfElements--;
            return value;
        }
        throw new NoSuchElementException("Element not found!");
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }
}
