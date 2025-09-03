package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void expandCapacity() {
        if (size == elementData.length) {
            int newCapacity = (int) (elementData.length * GROWTH_FACTOR);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index + "Size" + size);
        }
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index + "Size" + size);
        }
    }

    @Override
    public void add(T value) {
        expandCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkPositionIndex(index);
        expandCapacity();
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
        checkElementIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkElementIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        final T oldValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size - 1] = null;
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null && element == null) {
                return remove(i);
            }
            if (elementData[i] != null && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
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
