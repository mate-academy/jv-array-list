package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int CAPACITY_DIVISOR = 2;
    private int size;
    private Object[] data;

    public ArrayList() {
        this.size = 0;
        this.data = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        indexRangeCheckForAdd(index);
        grow();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexRangeCheck(index);
        return data(index);
    }

    @Override
    public void set(T value, int index) {
        indexRangeCheck(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        indexRangeCheck(index);
        T removedElement = data(index);
        if (index == size - 1) {
            System.arraycopy(data, 0, data, 0, size - 1);
        } else {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < data.length; i++) {
            if ((element == null && data[i] == element)
                    || (element != null && element.equals(data[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T data(int index) {
        return (T) data[index];
    }

    private void indexRangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds " + size);
        }
    }

    private void indexRangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds " + size);
        }
    }

    private void grow() {
        if (size == data.length) {
            int oldCapacity = data.length;
            int newCapacity = oldCapacity + oldCapacity / CAPACITY_DIVISOR;
            Object[] expandedData = new Object[newCapacity];
            System.arraycopy(data, 0, expandedData, 0, oldCapacity);
            data = expandedData;
        }
    }
}
