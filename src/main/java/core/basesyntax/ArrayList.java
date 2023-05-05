package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MINIMAL_GROWING_RANGE = 1.5;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfNeeded();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        growIfNeeded();
        if (size == index) {
            grow(1);
            elementData[size++] = value;
        } else {
            checkRangeForAdd(index);
            grow(1);
            System.arraycopy(elementData, index, elementData, index + 1,
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
        System.arraycopy(elementData, index + 1, elementData, index,
                size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementsAreEqual((T) elementData[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element: " + element);
    }

    private boolean elementsAreEqual(T firstElement, T secondElement) {
        return firstElement == secondElement
                || firstElement != null && firstElement.equals(secondElement);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkRangeForAdd(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No such index: " + index);
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            T[] newArray = (T[]) new Object[(int) Math.max(elementData.length
                    * MINIMAL_GROWING_RANGE, minCapacity)];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;
        }
    }

    private void grow(int capacity) {
        ensureCapacity(size + capacity);
    }

    private void growIfNeeded() {
        ensureCapacity(size + 1);
    }
}
