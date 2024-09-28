package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final int NEGATIVE_INDEX_MARKER = -1;
    private Object[] elementData;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "
                    + initialCapacity);
        }
    }

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    @Override
    public void add(T value) {
        if (size < this.elementData.length) {
            elementData[size] = value;
            size++;
        } else {
            add(value, size);
        }
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (size == this.elementData.length) {
            elementData = growIfArrayFull();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                    size - index);
        elementData[index] = value;
        size++;

    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private Object[] growIfArrayFull(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = (int)(oldCapacity * 1.5);
            Object[] elementDataNew = new Object[newCapacity];
            System.arraycopy(elementData,0, elementDataNew, 0, elementData.length);
            return elementDataNew;
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private Object[] growIfArrayFull() {
        return growIfArrayFull(size + 1);
    }

    @Override
    public void addAll(List<T> list) {
        Object[] elementDataNew = new Object[list.size()];
        for (int i = 0; i < elementDataNew.length; i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index <= size - 1 && index >= 0) {
            return (T) elementData[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    @Override
    public void set(T value, int index) {
        if (index <= size - 1 && index >= 0) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        final int newSize = size - 1;
        T element = (T) elementData[index];
        if (newSize > index) {
            System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
        }
        size = newSize;
        return element;
    }

    @Override
    public T remove(T element) {
        T removedElement = null;
        int index = NEGATIVE_INDEX_MARKER;
        for (int i = 0; i < size; i++) {
            if (elementData[i] != null && elementData[i].equals(element)) {
                index = i;
                removedElement = (T) elementData[i];
                break;
            }

            if (element == null && elementData[i] == null) {
                size--;
                return null;
            }
        }

        if (index == NEGATIVE_INDEX_MARKER) {
            throw new NoSuchElementException();
        }
        final int newSize = size - 1;
        System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
        size = newSize;
        return removedElement;
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
