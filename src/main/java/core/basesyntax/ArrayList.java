package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final int EMPTY_ARRAYLIST_SIZE = 0;
    private static final String INVALID_INDEX_ERROR_MESSAGE = 
            "Index should be bigger or equal then 0 and less then ArrayList size";
    private T[] dataArray;
    private int size;

    public ArrayList() {
        dataArray = (T[]) new Object[DEFAULT_SIZE];
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
        return dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T removedObject = dataArray[index];
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
        throw new NoSuchElementException(element.toString());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY_ARRAYLIST_SIZE;
    }

    private void growIfArrayFull() {
        if (size == dataArray.length) {
            dataArray = grow();
        }
    }

    private T[] grow() {
        int newLength = (size << 1) - (size >> 1);
        return createNewArray(dataArray, newLength);
    }

    private T[] createNewArray(T[] dataArray, int newLength) {
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(dataArray, 0, newArray, 0, dataArray.length);
        return newArray;
    }

    private void validateIndex(int index) {
        if ((index >= size && index != EMPTY_ARRAYLIST_SIZE) || index < EMPTY_ARRAYLIST_SIZE) {
            throw new ArrayListIndexOutOfBoundsException(INVALID_INDEX_ERROR_MESSAGE);
        }
    }
}
