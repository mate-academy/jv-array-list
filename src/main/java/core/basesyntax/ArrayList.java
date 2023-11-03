package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private static final int INDEX_NULL = 0;
    private Object [] array;
    private int arrayLength;
    private int elementsAmount;

    public ArrayList() {
        arrayLength = DEFAULT_ARRAY_SIZE;
        array = new Object [arrayLength];
    }

    @Override
    public void add(T value) {
        if (elementsAmount == arrayLength) {
            resize(arrayLength);
        }
        array[elementsAmount] = value;
        elementsAmount++;
    }

    @Override
    public void add(T value, int index) {
        if (index > elementsAmount || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        Object[]newArray = new Object[arrayLength];
        System.arraycopy(array, INDEX_NULL, newArray, INDEX_NULL, elementsAmount);
        System.arraycopy(array, index, newArray, index + 1, elementsAmount - index);
        newArray[index] = value;
        array = newArray;
        elementsAmount++;
        if (elementsAmount == arrayLength) {
            resize(arrayLength);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if ((list.size() + elementsAmount) > arrayLength) {
            resize(elementsAmount + list.size());
        }
        for (int i1 = 0; i1 < list.size(); i1++) {
            array[elementsAmount] = list.get(i1);
            elementsAmount++;
        }
    }

    @Override
    public T get(int index) {
        if (index >= elementsAmount || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= elementsAmount || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= elementsAmount) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        Object[]newArray = new Object[arrayLength];
        System.arraycopy(array, INDEX_NULL, newArray, INDEX_NULL, elementsAmount);
        elementsAmount--;
        System.arraycopy(array, index + 1, newArray, index, elementsAmount - (index));
        T indexElement = (T) array[index];
        array = newArray;
        return indexElement;
    }

    @Override
    public T remove(T element) {
        int foundElementIndex = -1;
        for (int i = 0; i < elementsAmount; i++) {
            if (equals(element, (T) array[i])) {
                foundElementIndex = i;
            }
        }
        if (foundElementIndex != -1) {
            remove(foundElementIndex);
        }
        if (foundElementIndex == -1) {
            throw new NoSuchElementException("Can`t find such element");
        }
        return element;
    }

    public boolean equals(T firstElement, T secondElement) {
        return firstElement == null && secondElement == null
                || firstElement != null && firstElement.equals(secondElement);
    }

    public void resize(int actualLength) {
        arrayLength = actualLength + DEFAULT_ARRAY_SIZE / 2;
        Object[]newArray = new Object[arrayLength];
        System.arraycopy(array, INDEX_NULL, newArray, INDEX_NULL, elementsAmount);
        array = newArray;
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
