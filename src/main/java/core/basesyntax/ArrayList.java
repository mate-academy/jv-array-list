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
        if (index <= arraySize && index >= 0) {
            System.arraycopy(elementData, index, elementData, index + 1, arraySize - index);
            elementData[index] = value;
            arraySize++;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid!");
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            growIfArrayFull();
            elementData[arraySize] = list.get(i);
            arraySize++;
        }
    }

    @Override
    public T get(int index) {
        return isIndexValid(index) ? elementData[index] : null;
    }

    @Override
    public void set(T value, int index) {
        if (isIndexValid(index)) {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        T oldData = null;
        growIfArrayFull();
        if (isIndexValid(index)) {
            oldData = elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, arraySize - index);
            arraySize--;
        }
        return oldData;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arraySize; i++) {
            if (elementData[i] == element || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " is invalid!");
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
        if (index < arraySize && index >= 0) {
            return true;
        }
        throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid!");
    }

    private void growIfArrayFull() {
        if (arraySize == elementData.length) {
            System.arraycopy(elementData, 0,
                    elementData = (T[]) new Object[(int) (arraySize * ARRAY_MULTIPLIER)],
                    0, arraySize);
        }
    }
}
