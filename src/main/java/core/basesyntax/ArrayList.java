package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int STANDART_ARRAY_LENGTH = 10;
    private static final double MAGNIFICATION_FACTOR = 1.5;
    private int arraySize = STANDART_ARRAY_LENGTH;
    private T [] elementsArray;
    private int size = 0;

    public ArrayList() {
        elementsArray = (T[]) new Object[arraySize];
    }

    @Override
    public void add(T value) {
        grow();
        elementsArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        grow();
        System.arraycopy(elementsArray, index, elementsArray, index + 1, size - index);
        elementsArray[index] = value;
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
        verifyIndex(index);
        return elementsArray[index];
    }

    @Override
    public void set(T value, int index) {
        verifyIndex(index);
        elementsArray[index] = value;
    }

    @Override
    public T remove(int index) {
        verifyIndex(index);
        T oldElement = elementsArray[index];
        System.arraycopy(elementsArray, index+1, elementsArray, index, size - index - 1);
        size --;
        return oldElement;
    }

    @Override
    public T remove(T element) {
        T oldElement;
        for (int i = 0; i < size; i ++) {
            if ((elementsArray[i] != null && elementsArray[i].equals(element))
                || (elementsArray[i] == null && element == null)) {
                oldElement = elementsArray[i];
                remove(i);
                return oldElement;
            }
        }
        throw new NoSuchElementException("Element doesn't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
        if (size >= elementsArray.length) {
            arraySize = (int) (arraySize * MAGNIFICATION_FACTOR);
            T [] newArray = (T[]) new Object[arraySize];
            System.arraycopy(elementsArray, 0, newArray, 0, elementsArray.length);
            elementsArray = newArray;
        }
    }

    public void verifyIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }
}
