package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size = 0;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkAndGrow();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAndGrow();
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
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
        T result = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == value || value != null && value.equals(elementData[i])) {
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

    private void checkAndGrow() {
        if (size == elementData.length) {
            T[] newArray = (T[]) new Object[elementData.length + (elementData.length >> 1)];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
