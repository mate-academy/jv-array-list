package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = (T[]) new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = (T[]) EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "
                    + initialCapacity);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    private void rangeCheckForGetSetRemove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    private T[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            T[] newElementData = (T[]) new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            return newElementData;
        } else {
            return elementData = (T[]) new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private T[] grow() {
        return grow(size);
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
        rangeCheckForAdd(index);
        if (size == elementData.length) {
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
        if (size < elementData.length) {
            elementData = grow(size + 1);
        }
        System.arraycopy(elementData, 0, elementData, size + 1, size + 1);
    }

    @Override
    public T get(int index) {
        rangeCheckForGetSetRemove(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForGetSetRemove(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForGetSetRemove(index);
        T deleteElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        size = size - 1;
        return deleteElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == element) || (elementData[i] != null
                    && elementData[i].equals(element))) {
                T deleteElement = elementData[i];
                System.arraycopy(elementData, i + 1, elementData, i, size - 1 - i);
                size = size - 1;
                return deleteElement;
            }
            if (i == size - 1) {
                throw new NoSuchElementException("NoSuchElementException");
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
}
