package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MINIMAL_GROWING_STEP = 1;
    private static final int MINIMAL_SIZE_LIST = 0;
    private static final int START_INDEX = 0;
    private static final double MINIMAL_GROWING_RANGE = 1.5;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    private void checkRangeForAdd(int index) {
        if (index >= size || index < MINIMAL_SIZE_LIST) {
            throw new ArrayListIndexOutOfBoundsException("No such index: " + index);
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            T[] newArray = (T[]) new Object[(int) Math.max(elementData.length
                    * MINIMAL_GROWING_RANGE, minCapacity)];
            System.arraycopy(elementData, START_INDEX, newArray, START_INDEX, size);
            elementData = newArray;
        }
    }

    private void grow(int capacity) {
        ensureCapacity(size + capacity);
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + MINIMAL_GROWING_STEP);
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size == index) {
            grow(MINIMAL_GROWING_STEP);
            elementData[size++] = value;
        } else {
            checkRangeForAdd(index);
            grow(MINIMAL_GROWING_STEP);
            System.arraycopy(elementData, index, elementData, index + MINIMAL_GROWING_STEP,
                    size - index);
            elementData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        grow(list.size());
        for (int i = 0; i < list.size(); i++) {
            elementData[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkRangeForAdd(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkRangeForAdd(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRangeForAdd(index);
        T removedValue = (T) elementData[index];
        System.arraycopy(elementData, index + MINIMAL_GROWING_STEP, elementData, index,
                size - index - MINIMAL_GROWING_STEP);
        elementData[--size] = null;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == MINIMAL_SIZE_LIST;
    }
}
