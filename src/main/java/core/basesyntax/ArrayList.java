package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private static final int MIN_CAPACITY_INCREMENT = 1;

    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
            + " is out of bounds for size " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
            + " is out of bounds for size " + size);
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int newCapacity = (int) Math.ceil(elementData.length * GROW_FACTOR);
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            if (newCapacity < DEFAULT_CAPACITY) {
                newCapacity = DEFAULT_CAPACITY;
            }

            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            this.elementData = newElementData;
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        if (listSize == 0) {
            return;
        }
        ensureCapacity(size + listSize);
        Object[] sourceArray;
        if (list instanceof ArrayList) {
            sourceArray = new Object[listSize];
            for (int i = 0; i < listSize; i++) {
                sourceArray[i] = list.get(i);
            }
        } else {
            sourceArray = new Object[listSize];
            for (int i = 0; i < listSize; i++) {
                sourceArray[i] = list.get(i);
            }
        }
        System.arraycopy(sourceArray, 0, elementData, size, listSize);
        size += listSize;
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
        T removedValue = (T) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? elementData[i] == null : element.equals(elementData[i])) {
                T removedValue = (T) elementData[i];
                int numMoved = size - 1 - i;
                if (numMoved > 0) {
                    System.arraycopy(elementData, i + 1, elementData, i, numMoved);
                }
                elementData[--size] = null;
                return removedValue;
            }
        }
        throw new NoSuchElementException("Element " + element + " not found in the list.");
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
