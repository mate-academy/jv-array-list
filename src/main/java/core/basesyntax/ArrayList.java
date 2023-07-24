package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] dataArray;
    private int size;

    public ArrayList() {
        dataArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (dataArray.length == size) {
            grow();
        }
        dataArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        if (dataArray.length == size) {
            grow();
        }
        indexCheck(index);
        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
        dataArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (dataArray.length == size) {
                grow();
            }
            dataArray[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (dataArray.length == size) {
            grow();
        }
        indexCheck(index);
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T removedElement = dataArray[index];
        size--;
        System.arraycopy(dataArray, index + 1, dataArray, index, size - index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray[i] == element || dataArray[i] != null
                    && dataArray[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("there is no such element" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        Object[] oldArray = dataArray;
        dataArray = (T[]) new Object[oldArray.length + (oldArray.length >> 1)];
        System.arraycopy(oldArray, 0, dataArray, 0, size);
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Size: " + size
                    + ", index input is " + index);
        }
    }
}
