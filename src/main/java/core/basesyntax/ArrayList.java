package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double INCREASE_RATE = 1.5;
    private T[] arrayOfElements;
    private int actualSize;

    public ArrayList() {
        arrayOfElements = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (actualSize == arrayOfElements.length) {
            increaseCapacity();
            arrayOfElements[actualSize] = value;
            actualSize++;
        } else {
            arrayOfElements[actualSize] = value;
            actualSize++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > actualSize) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " is out of bounds");
        }
        if (actualSize == arrayOfElements.length) {
            increaseCapacity();
        }
        System.arraycopy(arrayOfElements, index, arrayOfElements, index + 1, actualSize - index);
        arrayOfElements[index] = value;
        actualSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (checkIndexInRange(index)) {
            return arrayOfElements[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (checkIndexInRange(index)) {
            arrayOfElements[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (checkIndexInRange(index)) {
            final T removedElement = arrayOfElements[index];
            System.arraycopy(arrayOfElements, index + 1, arrayOfElements,
                    index, actualSize - index - 1);
            actualSize--;
            return removedElement;
        }
        return null;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < actualSize; i++) {
            if (arrayOfElements[i] == element || element != null
                    && element.equals(arrayOfElements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element: " + element + " is not found");
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public boolean isEmpty() {
        return actualSize == 0;
    }

    private boolean checkIndexInRange(int index) {
        if (index < 0 || index >= actualSize) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " is out of bounds");
        }
        return true;
    }

    private void increaseCapacity() {
        T[] temporaryArray = (T[]) new Object[(int) (arrayOfElements.length * INCREASE_RATE)];
        System.arraycopy(arrayOfElements, 0, temporaryArray, 0, actualSize);
        arrayOfElements = temporaryArray;
    }

}
