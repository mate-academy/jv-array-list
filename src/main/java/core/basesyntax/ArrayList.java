package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double ARRAY_MULTIPLIER = 1.5;
    private T[] elementData;
    private int arraySize;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elementData[arraySize] = value;
        arraySize++;
    }

    @Override
    public void add(T value, int index) {
        growIfArrayFull();
        if (index < arraySize && index >= 0 || index == arraySize) {
            T[] oldArray = (T[]) new Object[elementData.length];
            System.arraycopy(elementData, index, oldArray, index, arraySize - index);
            System.arraycopy(oldArray, index, elementData, index + 1, arraySize - index);
            elementData[index] = value;
            arraySize++;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Index passed to method is invalid!");
    }

    @Override
    public void addAll(List<T> list) {
        growIfArrayFull();
        for (int i = 0; i < list.size(); i++) {
            elementData[arraySize] = list.get(i);
            arraySize++;
            growIfArrayFull();
        }
    }

    @Override
    public T get(int index) {
        if (isIndexValid(index)) {
            return elementData[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index passed to method is invalid!");
    }

    @Override
    public void set(T value, int index) {
        if (isIndexValid(index)) {
            elementData[index] = value;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Index passed to method is invalid!");
    }

    @Override
    public T remove(int index) {
        growIfArrayFull();
        if (isIndexValid(index)) {
            T[] oldArray = (T[]) new Object[elementData.length];
            System.arraycopy(elementData, index + 1, oldArray, index, arraySize - index);
            T oldData = elementData[index];
            System.arraycopy(oldArray, index, elementData, index, arraySize - index);
            arraySize--;
            return oldData;
        }
        throw new ArrayListIndexOutOfBoundsException("Index passed to method is invalid!");
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < arraySize; i++) {
            if (elementData[i] == element || element != null && element.equals(elementData[i])) {
                index += (i + 1);
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException();
        }
        T[] oldArray = (T[]) new Object[elementData.length];
        System.arraycopy(elementData, index + 1, oldArray, index, arraySize - index);
        T oldData = elementData[index];
        System.arraycopy(oldArray, index, elementData, index, arraySize - index);
        arraySize--;
        return oldData;
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    private boolean isIndexValid(int index) {
        return index < arraySize && index >= 0;
    }

    private void growIfArrayFull() {
        if (arraySize == elementData.length) {
            T[] oldArray = (T[]) new Object[elementData.length];
            System.arraycopy(elementData, 0, oldArray, 0, oldArray.length);
            elementData = (T[]) new Object[(int) (arraySize * ARRAY_MULTIPLIER)];
            System.arraycopy(oldArray, 0, elementData, 0, oldArray.length);
        }
    }
}
