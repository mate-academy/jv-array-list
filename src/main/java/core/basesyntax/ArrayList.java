package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private Object[] elementData;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public ArrayList() {
        elementData = EMPTY_ELEMENT_DATA;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
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
        if (list == null) {
            return;
        }
        while (list.size() > elementData.length - size) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
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
        T oldValue = (T) elementData[index];
        fastRemove(index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == element
                    || elementData[i] != null && elementData[i].equals(element)) {
                fastRemove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Element " + element + " isn`t in collection");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != EMPTY_ELEMENT_DATA) {
            int newCapacity = (int) (oldCapacity * 1.5);
            elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private void grow() {
        grow(size + 1);
    }

    private void fastRemove(int i) {
        int newSize = size - 1;
        if ((newSize) > i) {
            System.arraycopy(elementData, i + 1, elementData, i, newSize - i);
        }
        elementData[size = newSize] = null;
    }
}
