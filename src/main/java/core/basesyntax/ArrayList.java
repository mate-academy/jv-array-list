package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private static final float EXPANSION_MULTIPLIER = 1.5F;
    private static final int ARRAY_ELEMENTS_OFFSET = 1;
    private static final int INVALID_INDEX = -1;
    private T[] elementArray;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.elementArray = (T[]) new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        if (isArrayHaveOneMoreLine()) {
            elementArray[size] = value;
            size++;
        } else {
            grow();
            add(value);
        }
    }

    @Override
    public void add(T value, int index) {
        exceptionCheckForAdd(index);
        if (index == size) {
            add(value);
            return;
        }
        if (isArrayHaveOneMoreLine()) {
            System.arraycopy(elementArray, index,
                    elementArray, index + ARRAY_ELEMENTS_OFFSET,
                    size - index);
            elementArray[index] = value;
            size++;
        } else {
            grow();
            add(value, index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        exceptionCheckForGetSetRemove(index);
        return elementArray[index];
    }

    @Override
    public void set(T value, int index) {
        exceptionCheckForGetSetRemove(index);
        elementArray[index] = value;
    }

    @Override
    public T remove(int index) {
        exceptionCheckForGetSetRemove(index);
        T returnValue = elementArray[index];
        System.arraycopy(elementArray, index + ARRAY_ELEMENTS_OFFSET,
                elementArray, index, size - index - ARRAY_ELEMENTS_OFFSET);
        size--;
        return returnValue;
    }

    @Override
    public T remove(T element) {
        int i = indexOfElementFinder(element);
        if (i < 0) {
            throw new NoSuchElementException("Element not found");
        }
        remove(i);
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        T[] newArray = (T[]) new Object[(int) (elementArray.length * EXPANSION_MULTIPLIER)];
        System.arraycopy(elementArray, 0, newArray, 0, size);
        elementArray = newArray;
    }

    private boolean isArrayHaveOneMoreLine() {
        return elementArray.length > size;
    }

    private int indexOfElementFinder(T element) {
        for (int i = 0; i < size; i++) {
            if ((elementArray[i] == element)
                    || (elementArray[i] != null
                    && elementArray[i].equals(element))) {
                return i;
            }
        }
        return INVALID_INDEX;
    }

    private void exceptionCheckForAdd(int index) {
        if (index < 0 || index > size) {
            arrayListExceptionThrow(index);
        }
    }

    private void exceptionCheckForGetSetRemove(int index) {
        if (index < 0 || index >= size) {
            arrayListExceptionThrow(index);
        }
    }

    private void arrayListExceptionThrow(int index) {
        throw new ArrayListIndexOutOfBoundsException("There is no such index!"
                + " Index bounds are from 0 to "
                + (size - 1) + ", but your index is " + index);
    }
}
