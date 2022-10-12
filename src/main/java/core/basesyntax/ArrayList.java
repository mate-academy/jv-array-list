package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double COEFFICIENT = 1.5;
    private Object[] elementOfObjets;
    private int numObjects;

    public ArrayList() {
        elementOfObjets = new Object[DEFAULT_SIZE];
        numObjects = 0;
    }

    @Override
    public void add(T value) {
        arrayIncrease();
        elementOfObjets[numObjects] = value;
        numObjects++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > numObjects) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        arrayIncrease();
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
        return (T) elementOfObjets[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementOfObjets[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object result = elementOfObjets[index];
        System.arraycopy(elementOfObjets, Math.min(index + 1, size()), elementOfObjets, index,
                size() - index - 1
        );
        numObjects--;
        return (T) result;
    }

    @Override
    public T remove(T element) {
        Object result = null;
        for (int i = 0; i < elementOfObjets.length; i++) {
            if (elementOfObjets[i] == null
                    ? element == elementOfObjets[i]
                    : elementOfObjets[i].equals(element)) {
                result = elementOfObjets[i];
                remove(i);
                return (T) result;
            }
        }
        throw new NoSuchElementException("Can`t find element " + element + " in list");
    }

    @Override
    public int size() {
        return numObjects;
    }

    @Override
    public boolean isEmpty() {
        return numObjects <= 0;
    }

    private void addElementInsideData(T value, int index) {
        System.arraycopy(elementOfObjets, index, elementOfObjets, index + 1, size() - index);
        elementOfObjets[index] = value;
        numObjects++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= numObjects) {
            throw new ArrayListIndexOutOfBoundsException("Index"
                    + index + " out of bounds for length" + numObjects);
        }
    }

    private void arrayIncrease() {
        if (numObjects >= elementOfObjets.length) {
            int newSize = (int) (numObjects * COEFFICIENT);
            Object[] newArray = new Object[newSize];
            System.arraycopy(elementOfObjets, 0, newArray, 0, elementOfObjets.length);
            elementOfObjets = newArray;
        }
    }
}
