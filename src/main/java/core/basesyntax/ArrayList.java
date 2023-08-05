package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIfIndexInRangeForAdd(index);
        growIfArrayFull();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        growIfArrayWillBecameFull(list.size());
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIfIndexInRange(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexInRange(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexInRange(index);
        T deletedElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1,
                elementData, index, size - index - 1);
        size--;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elementData[i] == null) {
                    System.arraycopy(elementData, i + 1,
                            elementData, i, size - i - 1);
                    size--;
                    return null;
                }
            } else if (element.equals(elementData[i])) {
                T elementToRemove = (T) elementData[i];
                System.arraycopy(elementData, i + 1,
                        elementData, i, size - i - 1);
                size--;
                return elementToRemove;
            }
        }
        throw new NoSuchElementException("element not found.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void growIfArrayFull() {
        if (elementData.length == size) {
            grow();
        }
    }

    private void growIfArrayWillBecameFull(int numberOfElementsToAdd) {
        if (size + numberOfElementsToAdd > elementData.length) {
            //increase the array by the missing number of elements plus one element
            grow(1 + size + numberOfElementsToAdd - elementData.length);
        }
    }

    private void grow() {
        int newCapacity = elementData.length + elementData.length / 2;
        increaseElementDataCapacity(newCapacity);
    }

    private void grow(int count) {
        if (count <= 0) {
            return;
        }
        int newCapacity = elementData.length + count;
        increaseElementDataCapacity(newCapacity);
    }

    private void increaseElementDataCapacity(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, elementData.length);
        elementData = newArray;
    }

    private void checkIfIndexInRangeForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + " is invalid.");
        }
    }

    private void checkIfIndexInRange(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + " is invalid.");
        }
    }
}
