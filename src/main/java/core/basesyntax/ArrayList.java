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
            ensureCapacity(size);
        }
        dataArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index is invalid");
        }
        if (size == dataArray.length) {
            ensureCapacity(size);
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
        if (dataArray.length < neededSize) {
            ensureCapacity(neededSize);
        }
        for (int i = size; i < neededSize; i++) {
            dataArray[i] = list.get(indexOfValueFromList++);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T returnedElement = dataArray[index];
        if (index < size - 1) {
            System.arraycopy(dataArray, index + 1, dataArray, index, size - index);
        }
        dataArray[size - 1] = null;
        size--;
        return returnedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < dataArray.length; i++) {
            if (element == null && dataArray[i] == null
                    || element != null && element.equals(dataArray[i])) {
                if (i < dataArray.length - 1) {
                    System.arraycopy(dataArray, i + 1, dataArray, i, size - i);
                }
                dataArray[size - 1] = null;
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

    private void ensureCapacity(int expandableSize) {
        double newCapacity = expandableSize * GROW_INDEX;
        T[] newArray = (T[]) new Object[(int) newCapacity];
        System.arraycopy(dataArray, 0, newArray, 0, dataArray.length);
        dataArray = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index is invalid");
        }
    }
}
