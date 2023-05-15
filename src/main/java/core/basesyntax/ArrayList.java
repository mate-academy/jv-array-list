package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE_FACTOR = 1.5;
    private T[] dataArray;
    int size = 0;

    public ArrayList() {
        dataArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        dataArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {

    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        checkIndexEqualsSize(index);
        checkIndex(index);
        return (T) dataArray[index];
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        get(index);
        T removedValue = dataArray[index];
        size--;
        System.arraycopy(dataArray, index + 1, dataArray, index, size - index);
        return removedValue;
    }

    @Override
    public T remove(T element) throws  NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if (dataArray[i] == element || dataArray[i] != null &&
            dataArray[i].equals(element)) {
                size--;
                return dataArray[i];
            }
        }
        throw new NoSuchElementException("No such elelement" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size == dataArray.length) {
            int oldLength = size;
            int newLength = (int) (oldLength * RESIZE_FACTOR);
            T[] newDataArray = (T[]) new Object[newLength];
            System.arraycopy(dataArray, 0, newDataArray, 0, size);
            dataArray = newDataArray;
        }
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is wrong: " + index);
        }
    }

    private void checkIndexEqualsSize(int index) {
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException("Index is wrong: " + index);
        }
    }
}
