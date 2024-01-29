package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
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
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of Bounds");
        }

        if (size == elementData.length) {
            grow();
        }

        System.arraycopy(elementData, index, elementData, index + 1,
                elementData.length - (index + 1));
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (size == elementData.length) {
                grow();
            }
            elementData[size] = list.get(i);
            size++;
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
        T value;
        checkIndex(index);
        value = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index,
                elementData.length - (index + 1));
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i]
                    || (element != null
                    && element.equals(elementData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element is not exist: "
                + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int newCapacity = elementData.length + (elementData.length >> 1);
        T[] oldElementData = elementData;
        elementData = (T[]) new Object[newCapacity];
        System.arraycopy(oldElementData, 0, elementData, 0,
                oldElementData.length);
    }

    private boolean checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index out of Bounds. Index have negative grade or greater then size of array:"
                            + index
                            + " "
                            + size);
        }
        return true;
    }
}
