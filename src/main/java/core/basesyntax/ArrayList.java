package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
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
        checkIndexToAdd(index);
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
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
        checkIndex(index);
        T oldValue = elementData[index];
        System.arraycopy(elementData, index + 1,
                elementData, index,
                size - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int elementIndex;
        for (int i = 0; i < size; i++) {
            if ((elementData[i] != null && elementData[i].equals(element))
                    || elementData[i] == element) {
                elementIndex = i;
                return remove(elementIndex);
            }
        }
        throw new NoSuchElementException("Element " + element + " does not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexToAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is not valid");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is not valid");
        }
    }

    private void grow() {
        int newCapacity = elementData.length + (elementData.length >> 1);
        Object[] copiedArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, copiedArray, 0, size);
        elementData = (T[]) copiedArray;
    }
}
