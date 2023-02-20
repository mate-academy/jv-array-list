package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private static final double MULTIPLIER = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_LENGTH];
    }

    public void grow() {
        int newLength = (int) (elementData.length * MULTIPLIER);
        T[] elementDataNewLength = (T[]) new Object[newLength];
        System.arraycopy(elementData, 0, elementDataNewLength, 0, elementData.length);
        elementData = elementDataNewLength;
    }

    public void growingCheck() {
        if (size == elementData.length) {
            grow();
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index Out Of Bounds");
        }
    }

    @Override
    public void add(T value) {
        growingCheck();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        growingCheck();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[size] = value;
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
        T obj = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        return obj;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == elementData[i] || elementData[i] != null)
                    && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
