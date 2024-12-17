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
        if (size >= data.length) {
            data = grow();
        }
        Object[] tempData = new Object[data.length];
        for (int i = index + 1; i <= size; i++) {
            tempData[i] = data[i - 1];
        }
        for (int i = 0; i <= index; i++) {
            if (index != i) {
                tempData[i] = data[i];
                continue;
            }
            tempData[i] = value;
        }
        size++;
        data = tempData;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = size + list.size();
        Object[] tempData = new Object[newSize];
        if (size < newSize && newSize > data.length) {
            tempData = grow(newSize);
        }
        for (int i = 0; i < size; i++) {
            tempData[i] = data[i];
        }
        for (int i = size, j = 0; i < newSize; i++, j++) {
            tempData[i] = list.get(j);
            size++;
        }
        data = tempData;
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
        for (int i = 0; i < size - 1; i++) {
            if (i >= index) {
                data[i] = data[i + 1];
            }
        }
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
        int oldSize = data.length;
        int newSize = (int) (oldSize * GROW_CAPACITY_COEFFICIENT);
        Object[] newData = new Object[newSize];
        for (int i = 0; i < oldSize; i++) {
            newData[i] = data[i];
        }
        return newData;
    }

    private Object[] grow(int newSize) {
        Object[] newData = new Object[newSize];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        return newData;
    }
}
