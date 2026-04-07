package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == elementData.length) {
            elementData = grow();
        }
        checkIndex(index);
        if (index == size + 1) {
            add(value);
        }
        if (index < size + 1) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] arrList = new Object[list.size()];
        while (size == elementData.length || size
                + list.size() > elementData.length) {
            elementData = grow();
        }
        for (int i = 0; i < list.size(); i++) {
            arrList[i] = list.get(i);
        }
        System.arraycopy(arrList, 0, elementData, size, arrList.length);
        size += list.size();
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
        final Object removedObject = null;
        checkIndex(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        elementData[size] = null;
        size--;
        return (T) removedObject;
    }

    @Override
    public T remove(T element) {
        int tempCount = 0;
        for (int i = 0; i <= size; i++) {
            if (!elementData[i].equals(element)) {
                tempCount++;
            }
        }
        if (tempCount == size) {
            throw new NoSuchElementException("There is no such element in the array");
        }
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                T theElement = (T) elementData[i];
                remove(i);
                return theElement;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        Object[] newData = new Object[elementData.length + elementData.length / 2];
        System.arraycopy(elementData, 0, newData, 0, size);
        return newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
    }
}
