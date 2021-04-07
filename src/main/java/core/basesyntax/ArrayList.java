package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] dataArray;
    private int size;

    public ArrayList(int capacity) {
        if (capacity >= 0) {
            this.dataArray = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Capacity can't be negative number");
        }
    }

    public ArrayList() {
        this.dataArray = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        dataArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        int maxAvailableSize = size + 1;
        indexCheck(index, maxAvailableSize);
        ensureCapacity(maxAvailableSize);
        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
        dataArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] listArray = list.toArray();
        ensureCapacity(size + listArray.length);
        System.arraycopy(listArray, 0, dataArray, size, listArray.length);
        size += listArray.length;
    }

    @Override
    public T get(int index) {
        indexCheck(index, size);
        return (T) dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index, size);
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index, size);
        T removedValue = (T) dataArray[index];
        fastRemove(index);
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == dataArray[i] || (dataArray[i] != null && element.equals(dataArray[i]))) {
                T removedValue = (T) dataArray[i];
                fastRemove(i);
                return removedValue;
            }
        }
        throw new NoSuchElementException("No such element present in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] listArray = new Object[this.size()];
        for (int i = 0; i < listArray.length; i++) {
            listArray[i] = this.get(i);
        }
        return listArray;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - dataArray.length > 0) {
            int newCapacity = dataArray.length + (dataArray.length >> 1);
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            dataArray = Arrays.copyOf(dataArray, newCapacity);
        }
    }

    private void indexCheck(int index, int maxAvailableSize) {
        if (index >= maxAvailableSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of list range");
        }
    }

    private void fastRemove(int index) {
        if (index != size - 1) {
            System.arraycopy(dataArray, index + 1, dataArray, index, size - index - 1);
        }
        dataArray[--size] = null;
    }
}
