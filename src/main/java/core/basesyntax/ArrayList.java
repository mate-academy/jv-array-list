package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_FACTOR = 1.5;
    private int size;
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY];
    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = (T[]) grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index: " + index
                    + " is out of bounds " + size);
        }

        Object[] elementData;
        if (size == (elementData = this.elementData).length) {
            elementData = grow();
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
        rangeCheckForAdd(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForAdd(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForAdd(index);
        T oldValue = (T) elementData[index];

        final int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
        }
        elementData[size = newSize] = null;

        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < elementData.length; i++) {
            if ((elementData[i] == null && element == null)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            return remove(index);
        } else {
            throw new NoSuchElementException("Element not found in the array");
        }
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
        int oldCapacity = elementData.length;
        int newCapacity = (int) (oldCapacity * INCREASE_FACTOR);
        T[] newArray = (T[])new Object[newCapacity];
        if (Math.min(size, newCapacity) >= 0) {
            System.arraycopy(elementData, 0, newArray, 0, Math.min(size, newCapacity));
        }
        elementData = newArray;
        return elementData;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
