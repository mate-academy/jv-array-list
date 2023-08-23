package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    public static final double PERCENTAGE_ARRAY_GROWTH = 1.5D;
    private Object[] elementData;
    private int size;

    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        elementData = new Object[initCapacity];
    }

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        elementData[size] = value;
        size++;
    }

    private void resizeIfNeeded() {
        if (elementData.length == size) {
            Object[] newArray = new Object[(int) (elementData.length * PERCENTAGE_ARRAY_GROWTH)];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;
        }
    }

    @Override
    public void add(T value, int index) {
        indexExsist(index, size);
        resizeIfNeeded();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        indexExsist(index, size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexExsist(index, size);
        elementData[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        indexExsist(index, size);
        T removedElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size --;

        return removedElement;
    }

    @Override
    public T remove(T element) {
        return  null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexExsist(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

}
