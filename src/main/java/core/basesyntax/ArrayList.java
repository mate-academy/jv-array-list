package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double COEFFICIENT = 1.5;
    private Object[] arrayOfElements;
    private int numObjects;

    public ArrayList() {
        arrayOfElements = new Object[DEFAULT_SIZE];
        numObjects = 0;
    }

    @Override
    public void add(T value) {
        arrayIncrease();
        arrayOfElements[numObjects] = value;
        numObjects++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > numObjects) {
            throw new ArrayListIndexOutOfBoundsException("Index:"
                    + index + "out of size" + numObjects);
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
        return (T) arrayOfElements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayOfElements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object result = arrayOfElements[index];
        System.arraycopy(arrayOfElements, Math.min(index + 1, size()), arrayOfElements, index,
                size() - index - 1
        );
        numObjects--;
        return (T) result;
    }

    @Override
    public T remove(T element) {
        Object result = null;
        for (int i = 0; i < arrayOfElements.length; i++) {
            if (arrayOfElements[i] == null
                    ? element == arrayOfElements[i]
                    : arrayOfElements[i].equals(element)) {
                result = arrayOfElements[i];
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
        System.arraycopy(arrayOfElements, index, arrayOfElements, index + 1, size() - index);
        arrayOfElements[index] = value;
        numObjects++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= numObjects) {
            throw new ArrayListIndexOutOfBoundsException("Index"
                    + index + " out of bounds for length" + numObjects);
        }
    }

    private void arrayIncrease() {
        if (numObjects >= arrayOfElements.length) {
            int newSize = (int) (numObjects * COEFFICIENT);
            Object[] newArray = new Object[newSize];
            System.arraycopy(arrayOfElements, 0, newArray, 0, arrayOfElements.length);
            arrayOfElements = newArray;
        }
    }
}
