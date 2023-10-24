package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private T[] elementData;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            resizeArray();
        }

        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throwArrayListIndexOutOfBoundsException();
        }

        if (size == elementData.length) {
            resizeArray();
        }

        shiftElementsToRight(index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() - DEFAULT_CAPACITY - 1 == 1) {
            elementData = (T[]) new Object[DEFAULT_CAPACITY + DEFAULT_CAPACITY / 2];

        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throwArrayListIndexOutOfBoundsException();
        }

        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throwArrayListIndexOutOfBoundsException();
        }

        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throwArrayListIndexOutOfBoundsException();
        }

        final T removedElement = elementData[index];

        shiftElementsToLeft(index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elementData[i] == null)
                    || (element != null && elementData[i] != null
                    && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void throwArrayListIndexOutOfBoundsException() {
        throw new ArrayListIndexOutOfBoundsException("Array List Index Out Of Bounds Exception");
    }

    private void resizeArray() {
        final int newCapacity = elementData.length + elementData.length / 2;
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }

    private void shiftElementsToRight(int startIndex) {
        System.arraycopy(elementData, startIndex, elementData,
                startIndex + 1, size - startIndex);
    }

    private void shiftElementsToLeft(int startIndex) {
        System.arraycopy(elementData, startIndex + 1, elementData,
                startIndex, size - startIndex - 1);
        elementData[size - 1] = null;
    }

}
