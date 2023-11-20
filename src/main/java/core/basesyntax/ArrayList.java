package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int index;
    private Object[] data = new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        try {
            ArrayList.this.add(value, index);
            index++;
        } catch (ArrayListIndexOutOfBoundsException e) {
            throw new RuntimeException("Index out of bounds!", e);
        }
    }

    @Override
    public void add(T value, int index) {
        indexRangeCheckForAdd(index);
        if (size == data.length) {
            data = grow();
        }
        if (data[index] == null) {
            data[index] = value;
            size++;
        } else {
            Object[] tempLeft = new Object[data.length];
            Object[] tempRight = new Object[data.length];
            System.arraycopy(data, 0, tempLeft, 0, index);
            System.arraycopy(data, index, tempRight, 0, data.length - index);
            data = new Object[data.length];
            System.arraycopy(tempLeft, 0, data, 0, index);
            data[index] = value;
            System.arraycopy(tempRight, 0, data, index + 1, data.length - index - 1);
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] listToArray = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listToArray[i] = list.get(i);
        }
        int newCapacity = size + listToArray.length;
        Object[] newData = new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        System.arraycopy(listToArray, 0, newData, size, listToArray.length);
        data = newData;
        size = newCapacity;
    }

    @Override
    public T get(int index) {
        indexRangeCheck(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        indexRangeCheck(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        indexRangeCheck(index);
        final T removedElement = (T) data[index];
        Object[] newData = new Object[size - 1];
        System.arraycopy(data, 0, newData, 0, index);
        if (index == size - 1) {
            data = newData;
        } else {
            System.arraycopy(data, index + 1, newData, index, size - index - 1);
            data = newData;
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = 0;
        int counter = 0;
        for (int i = 0; i < data.length; i++) {
            if ((element == null && data[i] == element)
                    || (element != null && element.equals(data[i]))) {
                index = i;
                counter++;
                break;
            }
        }
        if (counter == 0) {
            throw new NoSuchElementException("There is no such element in the list");
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

    private Object[] grow() {
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + oldCapacity / 2;
        Object[] expandedData = new Object[newCapacity];
        System.arraycopy(data, 0, expandedData, 0, oldCapacity);
        data = expandedData;
        return data;
    }
}
