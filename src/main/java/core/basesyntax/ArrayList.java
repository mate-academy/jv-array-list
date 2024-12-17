package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_CAPACITY_COEFFICIENT = 1.5;
    private Object[] data;
    private int size;

    public ArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= data.length) {
            data = grow();
        }
        data[size] = value;
        size += 1;
    }

    @Override
    public void add(T value, int index) {
        if (index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index
                    + " is out of bounds for the size "
                    + (size));
        }
        isIndexPositiveNumber(index);
        if (data.length < size + 1) {
            data = grow();
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = this.size + list.size();
        if (data.length < newSize) {
            data = grow(newSize);
        }
        for (int i = size, j = 0; i < newSize; i++, j++) {
            data[i] = list.get(j);
            size++;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        isIndexInBounds(index);
        isIndexPositiveNumber(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexInBounds(index);
        isIndexPositiveNumber(index);
        data[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        isIndexInBounds(index);
        isIndexPositiveNumber(index);
        T elementToRemove = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        int indexToRemove = 0;
        boolean isElementFound = false;
        for (int i = 0; i < size; i++) {
            if (data[i] == element || data[i] != null && data[i].equals(element)) {
                indexToRemove = i;
                isElementFound = true;
                break;
            }
        }
        if (!isElementFound) {
            throw new NoSuchElementException("No element found by value " + element);
        }
        return this.remove(indexToRemove);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    private void isIndexInBounds(int index) {
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index
                    + " is out of bounds for the size "
                    + (size));
        }
    }

    private void isIndexPositiveNumber(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index must be positive number"
            );
        }
    }

    private Object[] grow() {
        int newSize = (int) (data.length * GROW_CAPACITY_COEFFICIENT);
        return copyDataToExtendedArray(newSize);
    }

    private Object[] grow(int newSize) {
        return copyDataToExtendedArray(newSize);
    }

    private Object[] copyDataToExtendedArray(int newSize) {
        Object[] newData = new Object[newSize];
        System.arraycopy(data, 0, newData, 0, size);
        return newData;
    }
}
