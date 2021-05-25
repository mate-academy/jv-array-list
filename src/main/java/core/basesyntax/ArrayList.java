package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int arraySize;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (elementData.length == arraySize) {
            grow();
        }
        elementData[arraySize++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        if (index == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1,
                        arraySize - index);
        elementData[index] = value;
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
        checkIndex(index, arraySize);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, arraySize);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, arraySize);
        T elementToRemove = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index,
                        arraySize - index - 1);
        arraySize--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arraySize; i++) {
            if (element == elementData[i]
                    || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element is not found");
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
        T[] newElementData = (T[]) new Object[elementData.length
                            + elementData.length / 2];
        System.arraycopy(elementData, 0, newElementData, 0, arraySize);
        elementData = newElementData;
    }

    private void checkIndex(int index, int maxSize) {
        if (index < 0 || index >= maxSize) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }
}
