package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private transient T[] elementData;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        elementData = expandArray();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not within the array");
        }
        elementData = expandArray();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
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
        isIndexExist(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexExist(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexExist(index);
        final T value = elementData[index];
        T[] newElementData = elementData;
        elementData = (T[]) new Object[newElementData.length - 1];
        System.arraycopy(newElementData, 0, elementData, 0, index);
        System.arraycopy(newElementData, index + 1, elementData, index, newElementData.length
                - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can’t find the element " + element
                + " in the array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] expandArray() {
        if (size == elementData.length) {
            int newArraySize = (elementData.length) + (elementData.length >> 1);
            T[] newElementData = (T[]) new Object[newArraySize];
            System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
            return newElementData;
        }
        return elementData;
    }

    private void isIndexExist(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not within the array");
        }
    }
}
