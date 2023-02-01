package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        if (size == elementData.length) {
            grow();
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
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedIndex = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        size--;
        return removedIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] != null && elementData[i].equals(element)
                    || elementData[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in list: " + element);
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + "out of bounds size " + size);
        }
    }

    private Object[] grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + oldCapacity / 2;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, size);
        return elementData = newArray;
    }
}
