package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double resizeCoefficient = 1.5;
    private Object[] elementData;
    private int numberOfObjects;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        numberOfObjects = 0;
    }

    @Override
    public void add(T value) {
        ensureCapasity();
        elementData[numberOfObjects] = value;
        numberOfObjects++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > numberOfObjects) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        ensureCapasity();
        addElementInsideData(value, index);
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
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object result = elementData[index];
        System.arraycopy(elementData, Math.min(index + 1, size()), elementData, index,
                size() - index - 1);
        numberOfObjects--;
        return (T) result;
    }

    @Override
    public T remove(T element) {
        Object result = null;
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == null
                    ? element == elementData[i] : elementData[i].equals(element)) {
                result = elementData[i];
                remove(i);
                return (T) result;
            }
        }
        throw new NoSuchElementException("Can`t find element " + element + " in list");
    }

    @Override
    public int size() {
        return numberOfObjects;
    }

    @Override
    public boolean isEmpty() {
        return numberOfObjects <= 0;
    }

    private void addElementInsideData(T value, int index) {
        System.arraycopy(elementData, index, elementData, index + 1, size() - index);
        elementData[index] = value;
        numberOfObjects++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= numberOfObjects) {
            throw new ArrayListIndexOutOfBoundsException("Out of bound execution");
        }
    }

    private void ensureCapasity() {
        if (numberOfObjects >= elementData.length) {
            int newSize = (int) (numberOfObjects * resizeCoefficient);
            Object[] newArray = new Object[newSize];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;
        }
    }
}
