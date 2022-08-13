package core.basesyntax;

import java.util.Collection;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};
    Object[] elementData;
    private int size;
    private final int minCapacity = size + 1;

    public ArrayList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    private void rangeCheckForAddRemove(int index) {
        if (index > size || index < 0)
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
    }

    private void rangeCheckForGetSet(int index) {
        if (index >= size || index < 0)
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            return newElementData;
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private Object[] grow() {
        return grow(size + 1);
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
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        rangeCheckForAddRemove(index);
        if (index == elementData.length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size = size + 1;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
        if (size < elementData.length ) {
            elementData = grow(size + 1);
        }
        System.arraycopy(elementData, 0, elementData, size + 1, size + 1);
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        rangeCheckForGetSet(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        rangeCheckForGetSet(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        rangeCheckForAddRemove(index);
        if (index == size - 1) {
            System.arraycopy(elementData, 0, elementData, index - 1, size - 1);
        }
        return (T) elementData;
    }

    @Override
    public T remove(T element) throws ArrayListIndexOutOfBoundsException {

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
