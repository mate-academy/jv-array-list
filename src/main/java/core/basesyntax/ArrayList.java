package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int NO_INDEX = -1;
    private static final double DEFAULT_LOAD_FACTOR = 1.5;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayIsFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Requested index " + index + " is out of bounds");
        } else {
            growIfArrayIsFull();
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = elementData[index];
        final int newSize = size - 1;
        System.arraycopy(elementData, index + 1, elementData,
                index, newSize - index);
        elementData[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == NO_INDEX) {
            throw new NoSuchElementException(
                    "Requested element " + element + " is not in the list");
        }
        return remove(index);
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
        if (size == elementData.length) {
            int requiredCapacity = (int) (elementData.length * DEFAULT_LOAD_FACTOR);
            T[] newData = (T[])new Object[requiredCapacity];
            System.arraycopy(elementData, 0, newData, 0, size);
            elementData = newData;
        }
    }

    private int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == value || elementData[i] != null && elementData[i].equals(value)) {
                return i;
            }
        }
        return NO_INDEX;
    }

    private void checkIndex(int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Requested index " + index + " is out of bounds");
        }
    }
}
