package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_COEFFICIENT = 1.5;
    private static final int ARRAYS_ZERO_BORDER = 0;
    private static final int INCREASE_INDEX_FOR_COPY = 1;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < ARRAYS_ZERO_BORDER) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds array size");
        }
        ensureCapacity();
        System.arraycopy(elementData, index, elementData,
                index + INCREASE_INDEX_FOR_COPY,
                size - (index));
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
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldElement = elementData[index];
        System.arraycopy(elementData, index + INCREASE_INDEX_FOR_COPY,
                elementData, index, size - index - INCREASE_INDEX_FOR_COPY);
        elementData[--size] = null;
        return oldElement;
    }

    @Override
    public T remove(T element) {
        T oldElement = null;
        for (int i = 0; i < size; i++) {
            if (element == elementData[i]
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element here");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elementData.length == size) {
            int newCapacity = (int) (elementData.length * INCREASE_COEFFICIENT);
            T[] newElementData = (T[]) new Object[newCapacity];
            System.arraycopy(elementData, ARRAYS_ZERO_BORDER,
                    newElementData, ARRAYS_ZERO_BORDER, elementData.length);
            elementData = newElementData;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < ARRAYS_ZERO_BORDER) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds array size");
        }
    }
}
