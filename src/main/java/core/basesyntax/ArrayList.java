package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY];

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
        for (int i = size - 1; i >= startIndex; i--) {
            elementData[i + 1] = elementData[i];
        }
    }

    private void shiftElementsToLeft(int startIndex) {
        for (int i = startIndex; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
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
                final T removedElement = elementData[i];
                elementData[i] = null;
                shiftElementsToLeft(i);
                size--;
                return removedElement;
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

}
