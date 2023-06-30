package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            growTo(getNewCapacity(size));
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
        if (size == data.length) {
            growTo(getNewCapacity(size));
        }
        T[] temporaryData = (T[]) new Object[data.length];
        System.arraycopy(data, 0, temporaryData, 0, index);
        temporaryData[index] = value;
        System.arraycopy(data, index, temporaryData, index + 1, size - index);
        data = temporaryData;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size();i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (isIncorrectIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        if (isIncorrectIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isIncorrectIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
        T[] temporaryData = (T[]) new Object[data.length];
        System.arraycopy(data, 0, temporaryData, 0, index);
        System.arraycopy(data, index + 1, temporaryData, index, size - index - 1);
        T temporaryObject = data[index];
        data = temporaryData;
        size--;
        return temporaryObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size;i++) {
            if (data[i] != null && data[i].equals(element) || element == null) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element in this list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getNewCapacity(int oldCapacity) {
        return oldCapacity + (oldCapacity >> 1);
    }

    private void growTo(int valueOfGrow) {
        T[] temporaryData = (T[]) new Object[valueOfGrow];
        System.arraycopy(data, 0, temporaryData, 0, size);
        data = temporaryData;
    }

    private boolean isIncorrectIndex(int index) {
        return index >= size || index < 0;
    }
}
