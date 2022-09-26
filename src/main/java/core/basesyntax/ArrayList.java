package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROWTH_INDEX = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private T[] array;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (checkSize()) {
            array[currentSize] = value;
            currentSize++;
        } else {
            resizeArray();
            add(value);
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > currentSize) {
            throw new ArrayListIndexOutOfBoundsException("index [" + index + "] is invalid");
        }
        if (checkSize()) {
            System.arraycopy(array, index, array, index + 1, currentSize - index);
            array[index] = value;
            currentSize++;
        } else {
            resizeArray();
            add(value, index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() > (array.length - currentSize)) {
            resizeArray();
        }
        T[] listCopyArray = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listCopyArray[i] = list.get(i);
        }
        System.arraycopy(listCopyArray, 0, array, currentSize, listCopyArray.length);
        currentSize += listCopyArray.length;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = array[index];
        int newSize = currentSize - 1;
        System.arraycopy(array, index + 1, array, index, newSize - index);
        currentSize--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < currentSize; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " is'nt present in ArrayList");
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= currentSize) {
            throw new ArrayListIndexOutOfBoundsException("index [" + index + "] is invalid");
        }
    }

    private boolean checkSize() {
        return currentSize < array.length;
    }

    private void resizeArray() {
        T[] resizedArray = (T[]) new Object[(int) (array.length * GROWTH_INDEX)];
        System.arraycopy(array, 0, resizedArray, 0, array.length);
        array = resizedArray;
    }
}
