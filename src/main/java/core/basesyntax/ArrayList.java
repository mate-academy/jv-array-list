package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
        }
    }

    private Object[] grow() {
        int newCapacity;
        if (elementData.length > 1) {
            newCapacity = (int) (elementData.length * GROW_FACTOR);
        } else {
            newCapacity = elementData.length + 1;
        }
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, elementData.length);
        return newArray;
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
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index provided: " + index);
        }

        if (size == elementData.length) {
            elementData = grow();
        }

        System.arraycopy(elementData, index, elementData, index + 1, size - index);

        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int expectedSize = list.size() + size;
        while (expectedSize > elementData.length) {
            elementData = grow();
        }

        for (int i = 0; i < list.size(); i++) {
            elementData[size + i] = list.get(i);
        }

        size += list.size();
    }

    @Override
    public T get(int index) {
        if (checkForFaultyIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index provided: " + index);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (checkForFaultyIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index provided: " + index);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (checkForFaultyIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index provided: " + index);
        }

        final T value = (T) elementData[index];

        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);

        elementData[size - 1] = null;
        size--;
        return value;
    }

    @Override
    public T remove(T element) {

        int index = -1;
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == element && element == null)
                    || (element != null && element.equals(this.elementData[i]))) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            return remove(index);
        } else {
            throw new NoSuchElementException("Couldn't find requested element: "
                    + element);
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

    private boolean checkForFaultyIndex(int index) {
        return index >= size || index < 0;
    }
}
