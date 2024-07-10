package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_INDEX = 1.5;
    private static final int START_INDEX_VALUE = 0;
    private T[] dataArray;
    private int size;

    public ArrayList() {
        dataArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        dataArray = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == dataArray.length) {
            dataArray = grow();
        }
        dataArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index is invalid");
        }
        if (size == dataArray.length) {
            dataArray = grow();
        }
        if (index == size) {
            dataArray[size++] = value;
        } else {
            System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
            dataArray[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        int neededSize = size + list.size();
        int indexOfValueFromList = START_INDEX_VALUE;
        if (size == dataArray.length || dataArray.length < neededSize) {
            dataArray = growToNeededSize(neededSize);
        }
        for (int i = size; i < neededSize; i++) {
            dataArray[i] = list.get(indexOfValueFromList++);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        return remove(dataArray[index]);
    }

    @Override
    public T remove(T element) {
        T[] temp = (T[]) new Object[Math.max(DEFAULT_CAPACITY, dataArray.length - 1)];
        for (int i = 0; i < dataArray.length; i++) {
            if (element == null && dataArray[i] == null
                    || element != null && element.equals(dataArray[i])) {
                for (int index = 0; index < i; index++) {
                    temp[index] = dataArray[index];
                }
                for (int j = i; j < dataArray.length - 1; j++) {
                    temp[j] = dataArray[j + 1];
                }
                System.arraycopy(temp, 0, dataArray, 0, temp.length);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("This element does not belong to this list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        double newCapacity = dataArray.length * GROW_INDEX;
        T[] newArray = (T[]) new Object[(int) newCapacity];
        System.arraycopy(dataArray, 0, newArray, 0, dataArray.length);
        return newArray;
    }

    private T[] growToNeededSize(int neededSize) {
        while (neededSize > dataArray.length) {
            dataArray = grow();
        }
        return dataArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index is invalid");
        }
    }
}
