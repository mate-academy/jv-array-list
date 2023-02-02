package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_DEFAULT_LENGTH = 10;
    private Object[] array;
    private int currentSize;

    public ArrayList() {
        array = new Object[ARRAY_DEFAULT_LENGTH];
        currentSize = 0;
    }

    @Override
    public void add(T value) {
        lengthCheck(currentSize);
        array[currentSize] = value;
        currentSize++;
    }

    @Override
    public void add(T value, int index) {
        try {
            lengthCheck(currentSize);
            Object[] arrayTail = Arrays.copyOfRange(array, index, currentSize);
            array[index] = value;
            System.arraycopy(arrayTail, 0, array, index + 1, arrayTail.length);
            currentSize++;
        } catch (RuntimeException e) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Can't add element. Index " + index + " is out of bounds"
                            + " for the list size " + currentSize);
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] arrayToBeAdded = new Object[list.size()];
        int newSize = currentSize + arrayToBeAdded.length;
        lengthCheck(newSize);
        for (int i = 0; i < list.size(); i++) {
            arrayToBeAdded[i] = list.get(i);
        }
        System.arraycopy(arrayToBeAdded, 0, array, currentSize, arrayToBeAdded.length);
        currentSize = newSize;
    }

    @Override
    public T get(int index) {
        if (isIndexValid(index)) {
            return (T) array[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException(
                    "Can't get element. Index " + index + " is out of bounds"
                            + " for the list size " + currentSize);
        }
    }

    @Override
    public void set(T value, int index) {
        if (isIndexValid(index)) {
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException(
                    "Can't set element. Index " + index + " is out of bounds"
                            + " for the list size " + currentSize);
        }
    }

    @Override
    public T remove(int index) {
        if (isIndexValid(index)) {
            currentSize--;
            return removeElement(index);
        } else {
            throw new ArrayListIndexOutOfBoundsException(
                    "Can't remove element. Index " + index + " is out of bounds"
                            + " for the list size " + currentSize);
        }
    }

    @Override
    public T remove(T element) {
        try {
            for (int i = 0; i < currentSize; i++) {
                if (Objects.equals(array[i], element)) {
                    currentSize--;
                    return removeElement(i);
                }
            }
        } catch (RuntimeException e) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Can't remove element for list size " + currentSize);
        }
        throw new NoSuchElementException(
                "Can't remove element. There is no such element "
                        + element + " in the List");
    }

    private T removeElement(int index) {
        Object removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        return (T) removedElement;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void lengthCheck(int length) {
        if (array.length <= length) {
            grow();
        }
    }

    private void grow() {
        array = Arrays.copyOf(array, currentSize + (currentSize >> 1));
    }

    private boolean isIndexValid(int index) {
        return index >= 0 && index < currentSize;
    }
}
