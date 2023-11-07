package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T [] array;
    private int arrayLength;
    private int elementsAmount;

    public ArrayList() {
        arrayLength = DEFAULT_ARRAY_SIZE;
        array = (T[]) new Object [arrayLength];
    }

    private void checkIndex(int index) {
        if (index > elementsAmount || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound: " + index);
        }
        if (elementsAmount == arrayLength) {
            resize(arrayLength);
        }
    }

    private boolean equals(T firstElement, T secondElement) {
        return firstElement == null && secondElement == null
                || firstElement != null && firstElement.equals(secondElement);
    }

    private void resize(int actualLength) {
        arrayLength = (int) (actualLength * GROWTH_FACTOR);
        Object[]newArray = new Object[arrayLength];
        System.arraycopy(array, 0, newArray, 0, elementsAmount);
        array = (T[])newArray;
    }

    @Override
    public void add(T value) {
        checkIndex(elementsAmount);
        array[elementsAmount] = value;
        elementsAmount++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        System.arraycopy(array, index, array, index + 1, elementsAmount - index);
        array[index] = value;
        elementsAmount++;
        if (elementsAmount == arrayLength) {
            resize(arrayLength);
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
        checkIndex(index + 1);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index + 1);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index + 1);
        T indexElement = array[index];
        System.arraycopy(array, index + 1, array, index, elementsAmount - 1 - index);
        elementsAmount--;
        return indexElement;
    }

    @Override
    public T remove(T element) {
        int foundElementIndex = -1;
        for (int i = 0; i < elementsAmount; i++) {
            if (equals(element, array[i])) {
                foundElementIndex = i;
                remove(i);
                break;
            }
        }
        if (foundElementIndex == -1) {
            throw new NoSuchElementException("Can't find element " + element);
        }
        return element;
    }

    @Override
    public int size() {
        return elementsAmount;
    }

    @Override
    public boolean isEmpty() {
        return elementsAmount == 0;
    }
}
