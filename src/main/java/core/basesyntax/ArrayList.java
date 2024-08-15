package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int NO_INDEX = -1;
    private static final double DEFAULT_LOAD_FACTOR = 1.5;
    private int size;
    private int currentCapacity = DEFAULT_CAPACITY;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        elementData[size] = value;
        size++;
        growIfArrayIsFull();
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Requested index " + index + " is out of bounds");
        } else {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
            growIfArrayIsFull();
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] buffer = toArray(list);
        Object[] elementData;
        int requiredCapacity = list.size() + size;
        if (requiredCapacity >= currentCapacity) {
            grow(requiredCapacity);
        }
        System.arraycopy(buffer, 0, elementData = this.elementData, size, list.size());
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (isIndexOutOfSize(index)) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Requested index " + index + " is out of bounds");
        } else {
            return elementData[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (isIndexOutOfSize(index)) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Requested index " + index + " is out of bounds");
        } else {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (isIndexOutOfSize(index)) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Requested index " + index + " is out of bounds");
        } else {
            final int newSize;
            T oldValue = elementData[index];
            if ((newSize = size - 1) > index) {
                System.arraycopy(elementData, index + 1, elementData,
                        index, newSize - index);
            }
            elementData[size = newSize] = null;
            return oldValue;
        }
    }

    @Override
    public T remove(T element) {
        int newSize;
        int index = indexOf(element);
        if (index != NO_INDEX) {
            if ((newSize = size - 1) > index) {
                System.arraycopy(elementData, index + 1, elementData,
                        index, newSize - index);
            }
            elementData[size = newSize] = null;
        } else {
            throw new NoSuchElementException(
                    "Requested element " + element + " is not in the list");
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayIsFull() {
        if (size == currentCapacity) {
            currentCapacity = (int) (currentCapacity * DEFAULT_LOAD_FACTOR);
            increaseCapacity();
        }
    }

    private void grow(int minCapacity) {
        currentCapacity = (int) (minCapacity * DEFAULT_LOAD_FACTOR);
        increaseCapacity();
    }

    private void increaseCapacity() {
        Object[] newData = new Object[currentCapacity];
        for (int i = 0; i < elementData.length; i++) {
            newData[i] = elementData[i];
        }
        elementData = (T[]) newData;
    }

    private int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == value || elementData[i] != null && elementData[i].equals(value)) {
                return i;
            }
        }
        return NO_INDEX;
    }

    private boolean isIndexOutOfSize(int index) {
        return index >= size || index < 0;
    }

    private Object[] toArray(List<T> list) {
        Object[] array = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
