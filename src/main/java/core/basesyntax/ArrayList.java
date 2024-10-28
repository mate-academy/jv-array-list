package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            growIfArrayFull();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexToAdd(index);
        if (size == elementData.length) {
            growIfArrayFull();
        }
        divideAndSetNewValue(value, index);
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int arrayLength = list.size();
        Object[] listArray = new Object[arrayLength];
        if (arrayLength == 0) {
            return;
        }
        for (int i = 0; i < arrayLength; i++) {
            listArray[i] = list.get(i);
        }
        while (arrayLength > elementData.length - size) {
            grow();
        }
        System.arraycopy(listArray, 0, elementData, size, arrayLength);
        size += arrayLength;
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
        T oldValue = divideAndRemoveValue(index);
        size--;
        return oldValue;

    }

    @Override
    public T remove(T element) {
        int elementIndex;
        for (int i = 0; i < elementData.length; i++) {
            if ((elementData[i] != null && elementData[i].equals(element))
                    || elementData[i] == null && element == null) {
                elementIndex = i;
                size--;
                return divideAndRemoveValue(elementIndex);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void checkIndexToAdd(int index) throws ArrayListIndexOutOfBoundsException {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is not valid");
        }
    }

    public void checkIndex(int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is not valid");
        }
    }

    private void growIfArrayFull() {
        if (size == elementData.length) {
            grow();
        }
    }

    private void grow() {
        int newCapacity = elementData.length + (elementData.length >> 1);
        Object[] copiedArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, copiedArray, 0, size);
        elementData = copiedArray;
    }

    private void divideAndSetNewValue(T value, int index) {
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = value;
    }

    private T divideAndRemoveValue(int index) {
        T oldValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1,
                elementData, index,
                size - index - 1);
        return oldValue;
    }
}
