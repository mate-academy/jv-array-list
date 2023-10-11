package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ARRAY_IS_EMPTY = 0;
    private Object[] elementData;
    private int currentSize;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elementData[currentSize++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, currentSize);
        growIfArrayFull();
        if (index == currentSize) {
            elementData[currentSize++] = value;
            return;
        }
        int tailLength = currentSize - index;
        System.arraycopy(elementData, index, elementData, index + 1, tailLength);
        elementData[index] = value;
        currentSize++;
    }

    @Override
    public void addAll(List<T> list) {
        int minLength = Math.max(list.size() + currentSize, DEFAULT_CAPACITY);
        grow(minLength);
        for (int i = 0; i < list.size(); i++) {
            elementData[currentSize++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, currentSize - 1);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, currentSize - 1);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, currentSize - 1);
        T elemToRemove = (T) elementData[index];
        if (index == currentSize) {
            elementData[index] = null;
            return elemToRemove;
        }
        int tailLength = currentSize - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, tailLength);
        currentSize--;
        return elemToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < currentSize; i++) {
            if ((elementData[i] != null && elementData[i].equals(element))
                    || element == elementData[i]) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Cant find the  " + element);
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == ARRAY_IS_EMPTY;
    }

    private void growIfArrayFull() {
        if (currentSize == elementData.length) {
            grow(elementData.length);
        }
    }

    private void grow(int minLength) {
        int newCapacity = (int) (minLength + (minLength * 1.5));
        Object[] tmp = new Object[newCapacity];
        System.arraycopy(elementData, 0, tmp, 0, currentSize);
        elementData = tmp;
    }

    private void checkIndex(int index, int upperBound) {
        if (index < 0 || index > upperBound) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d out of bounds for size %d", index, currentSize));
        }
    }
}
