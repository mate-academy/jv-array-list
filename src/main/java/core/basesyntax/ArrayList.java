package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementsArray;
    private int arraySize;

    public ArrayList() {
        elementsArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        elementsArray = grow();
        elementsArray[arraySize++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > arraySize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index!");
        } else {
            if (arraySize == elementsArray.length) {
                elementsArray = grow();
            }
            System.arraycopy(elementsArray, index, elementsArray, index + 1, arraySize - index);
            elementsArray[index] = value;
            arraySize++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            elementsArray[arraySize++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index!");
        } else {
            return elementsArray[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index!");
        } else {
            if (arraySize == elementsArray.length) {
                elementsArray = grow();
            }
            System.arraycopy(elementsArray, 0, elementsArray, index + 1, arraySize);
            elementsArray[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index!");
        } else {
            T removedElement = elementsArray[index];
            System.arraycopy(elementsArray, index + 1, elementsArray, index, arraySize - index - 1);
            arraySize--;
            return removedElement;
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementsArray.length; i++) {
            if (elementsArray[i] == element
                    || elementsArray[i] != null && elementsArray[i].equals(element)) {
                modifyAfterRemoval(i);
                return element;
            }
        }
        throw new NoSuchElementException("There is no such element present!");
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    private boolean checkIndex(int index) {
        return index >= arraySize || index < 0;
    }

    public T[] grow() {
        if (arraySize == elementsArray.length) {
            return elementsArray = Arrays.copyOf(elementsArray, newCapacity());
        }
        return elementsArray;
    }

    private int newCapacity() {
        return elementsArray.length + (elementsArray.length >> 1);
    }

    private void modifyAfterRemoval(int index) {
        System.arraycopy(elementsArray, index + 1, elementsArray, index, arraySize - index - 1);
        elementsArray[arraySize--] = null;
    }
}
