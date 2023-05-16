package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_RATE = 1.5;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (elementData.length == size) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid " + index);
        }
        if (elementData.length == size) {
            grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexValidation(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        final T removedValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (areEqualElement(element, elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " is not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int newSize = (int) (elementData.length * GROWTH_RATE);
        T[] bufferArray = (T[]) new Object[newSize];
        System.arraycopy(elementData, 0, bufferArray, 0, size());
        elementData = bufferArray;
    }

    private void indexValidation(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is not valid");
        }
    }

    private boolean areEqualElement(T firstElement, T secondElement) {
        return firstElement == secondElement
                || (firstElement != null && firstElement.equals(secondElement));
    }
}
