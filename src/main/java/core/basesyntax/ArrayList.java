package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = EMPTY_ELEMENTDATA;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow(size + 1);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index, size);
        if (size == elementData.length) {
            elementData = grow(size + 1);
        }
        System.arraycopy(elementData, index, elementData,
                index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = size + list.size();
        if (newSize > elementData.length) {
            elementData = grow(newSize);
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
        size = newSize;
    }

    @Override
    public T get(int index) {
        checkIndex(index, size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T oldValue = (T) elementData[index];
        removeElement(index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int newCapacity(int minCapacity) {
        if (elementData == EMPTY_ELEMENTDATA) {
            return DEFAULT_CAPACITY;
        }
        int newCapacity = elementData.length + (elementData.length >> 1);
        return Math.max(minCapacity, newCapacity);
    }

    private Object[] grow(int minCapacity) {
        Object[] newElementData = new Object[newCapacity(minCapacity)];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        return newElementData;
    }

    private void checkIndex(int index, int length) {
        if (index < 0 || index >= length) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds!");
        }
    }

    private void checkIndexForAdd(int index, int length) {
        if (index < 0 || index > length) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds!");
        }
    }

    private void removeElement(int index) {
        size--;
        if (size > index) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index);
        }
        elementData[size] = null;
    }
}
