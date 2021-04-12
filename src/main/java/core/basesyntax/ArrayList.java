package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double INCREASING_COEFFICIENT = 1.5;

    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow(elementData.length);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index passed to the method is invalid");
        }
        if (size == elementData.length) {
            elementData = grow(elementData.length);
        }
        System.arraycopy(elementData, index, elementData,
                index + 1, size - index);
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
        return removeValue(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                return removeValue(i);
            }
        }
        throw new NoSuchElementException("There is no such element present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index passed to the method is invalid");
        }
    }

    private T[] grow(int oldSize) {
        T[] newElementData = (T[]) new Object[(int) (oldSize * INCREASING_COEFFICIENT)];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        return newElementData;
    }

    private T removeValue(int index) {
        T oldValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return oldValue;
    }
}
