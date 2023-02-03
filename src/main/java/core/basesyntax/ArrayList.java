package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_DEFAULT_LENGTH = 10;
    private T[] array;
    private int currentSize;

    public ArrayList() {
        array = (T[]) new Object[ARRAY_DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        lengthCheck();
        array[currentSize] = value;
        currentSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index <= currentSize && index >= 0) {
            lengthCheck();
            T[] arrayTail = Arrays.copyOfRange(array, index, currentSize);
            array[index] = value;
            System.arraycopy(arrayTail, 0, array, index + 1, arrayTail.length);
            currentSize++;
        } else {
            throw new ArrayListIndexOutOfBoundsException(
                    "Can't add element. Index " + index + " is out of bounds"
                            + " for the list size " + currentSize);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isIndexValid(index);
        return (T) array[index];

    }

    @Override
    public void set(T value, int index) {
        isIndexValid(index);
        array[index] = value;

    }

    @Override
    public T remove(int index) {
        isIndexValid(index);
        return removeElement(index);

    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < currentSize; i++) {
            if (element == null && array[i] == null
                    || element != null && element.equals(array[i])) {
                return removeElement(i);
            }
        }
        throw new NoSuchElementException(
                "Can't remove element. There is no such element "
                        + element + " in the List");
    }

    private T removeElement(int index) {
        T removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        currentSize--;
        return removedElement;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void lengthCheck() {
        if (array.length <= currentSize) {
            grow();
        }
    }

    private void grow() {
        T[] newArray = (T[]) new Object[currentSize + (currentSize >> 1)];
        System.arraycopy(array, 0, newArray, 0, currentSize);
        array = newArray;
    }

    private void isIndexValid(int index) {
        if (index >= 0 && index < currentSize) {
            return;
        }
        throw new ArrayListIndexOutOfBoundsException(
                "Index " + index + " is out of bounds"
                        + " for the list size " + currentSize);
    }
}
