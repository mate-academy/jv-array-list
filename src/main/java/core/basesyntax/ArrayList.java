package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY];

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
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds Exception");
        }

        if (size == elementData.length) {
            resizeArray();
        }

        shiftElementsToRight(index);
        elementData[index] = value;
        size++;
    }

    private void resizeArray() {
        int newCapacity = elementData.length + elementData.length / 2;
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }

    private void shiftElementsToRight(int startIndex) {
        for (int i = size - 1; i >= startIndex; i--) {
            elementData[i + 1] = elementData[i];
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() - DEFAULT_CAPACITY - 1 == 1) {
            for (int i = 0; i < list.size(); i++) {
                elementData = (T[]) new Object[DEFAULT_CAPACITY + DEFAULT_CAPACITY / 2];
                elementData[size] = list.get(i);
                size++;
                return;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("sfds");
        }
        if (index < size) {
            return elementData[index];
        }
        throw new ArrayListIndexOutOfBoundsException("df");
    }

    @Override
    public void set(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("sfds");
        }
        if (index < size) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("sfds");
        }

    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }

        final T removedElement = elementData[index];

        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }

        elementData[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    final T removedElement = elementData[i];
                    elementData[i] = null;
                    shiftElementsToLeft(i);
                    size--;
                    return removedElement;
                }
            }

        } else {
            for (int i = 0; i < size; i++) {
                if (elementData[i] != null && elementData[i].equals(element)) {
                    final T removedElement = elementData[i];
                    elementData[i] = null;
                    shiftElementsToLeft(i);
                    size--;
                    return removedElement;
                }
            }
        }

        throw new NoSuchElementException("Element not found");
    }

    private void shiftElementsToLeft(int startIndex) {
        for (int i = startIndex; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
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
