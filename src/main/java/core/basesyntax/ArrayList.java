package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_SIZE = 0;
    private static final Object[] DEFAULT_EMPTY_DATA_ARRAY = {};
    private static final String INVALID_INDEX_ERROR_MESSAGE = 
            "Index should be bigger or equal then 0 and less then ArrayList size";

    private Object[] dataArray;
    private int size;

    ArrayList() {
        dataArray = DEFAULT_EMPTY_DATA_ARRAY;
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        dataArray[size] = value;
        ++size;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        validateIndex(index);
        growIfArrayFull();
        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
        dataArray[index] = value;
        ++size;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return (T) dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T removedObject = (T) dataArray[index];
        if (index < size - 1) {
            System.arraycopy(dataArray, index + 1, dataArray, index, size - index);
        } else {
            dataArray[index] = null;
        }
        --size;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray[i] != null && dataArray[i].equals(element) || element == dataArray[i]) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == DEFAULT_SIZE;
    }

    private void growIfArrayFull() {
        if (size == dataArray.length) {
            dataArray = grow();
        }
    }

    private Object[] grow() {
        if (dataArray != DEFAULT_EMPTY_DATA_ARRAY) {
            int newLength = (size << 1) - (size >> 1);
            return Arrays.copyOf(dataArray, newLength);
        }
        return Arrays.copyOf(dataArray, DEFAULT_CAPACITY);
    }

    private void validateIndex(int index) {
        if ((index >= size && index != DEFAULT_SIZE) || index < DEFAULT_SIZE) {
            throw new ArrayListIndexOutOfBoundsException(INVALID_INDEX_ERROR_MESSAGE);
        }
    }
}
