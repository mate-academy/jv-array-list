package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_CAPACITY = 1.5;
    private static final int ARRAYS_ZERO_BORDER = 0;
    private static final int INCREASE_INDEX_FOR_COPY = 1;
    private T[] elementData;
    private int size;

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
        if (elementData.length == size) {
            grow();
        }
        size++;
        if (checkIndex(index)) {
            System.arraycopy(elementData, index, elementData,
                    index + INCREASE_INDEX_FOR_COPY,
                    size - (index + INCREASE_INDEX_FOR_COPY));
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds array size");
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (elementData.length < size + list.size()) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (checkIndex(index)) {
            return elementData[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds array size");
        }
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds array size");
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < ARRAYS_ZERO_BORDER) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds array size");
        }
        T oldElement = elementData[index];
        System.arraycopy(elementData, index + INCREASE_INDEX_FOR_COPY,
                elementData, index, size - index - INCREASE_INDEX_FOR_COPY);
        size--;
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

    public void grow() {

        int newCapacity = (int) (elementData.length * INCREASE_CAPACITY);
        T[] newElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, ARRAYS_ZERO_BORDER,
                newElementData, ARRAYS_ZERO_BORDER, elementData.length);
        elementData = newElementData;
    }

    private boolean checkIndex(int index) {
        return index < size && index >= ARRAYS_ZERO_BORDER;
    }
}
