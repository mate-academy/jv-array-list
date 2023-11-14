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
        ensureCapacityInternal(size + 1);
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData,index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacityInternal(size + list.size());
        for (int i = 0; i < list.size(); i++) {
            elementData[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T removedElement = (T) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(Object element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i])) {
                    return remove(i);
                }
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

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("No such index: " + index);
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (minCapacity > elementData.length) {
            int newCapacity = Math.max(elementData.length * 3 / 2 - 1, minCapacity);
            Object[] newArray = new Object[newCapacity];
            if (size >= 0) {
                System.arraycopy(elementData,0, newArray, 0, size);
            }
            elementData = newArray;
        }
    }
}
