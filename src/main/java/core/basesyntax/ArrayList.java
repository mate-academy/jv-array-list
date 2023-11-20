package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] data = new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        if (size == data.length) {
            data = grow();
        }
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        indexRangeCheckForAdd(index);
        if (size == data.length) {
            data = grow();
        }
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
