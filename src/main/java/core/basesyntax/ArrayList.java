package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private int size;
    private T[] dataArray;

    public ArrayList() {
        this.dataArray = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        checkAndGrowArraySize();
        this.dataArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size);
        if (index == size) {
            add(value);
            return;
        }
        checkAndGrowArraySize();
        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
        this.dataArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("You can't add the list with Null");
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, size - 1);
        return dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size - 1);
        this.dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        T removedValue = get(index);
        if (index == size - 1) {
            this.dataArray[index] = null;
            size--;
            return removedValue;
        }
        System.arraycopy(dataArray, index + 1, dataArray, index, size - index);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size - 1; i++) {
            if ((element == dataArray[i])
                    || (dataArray[i] != null && dataArray[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("The list doesn't contain this element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkAndGrowArraySize() {
        if (size == dataArray.length) {
            T[] temporaryArray = (T[]) new Object[size + (size >> 1)];
            System.arraycopy(dataArray, 0, temporaryArray, 0, size);
            this.dataArray = temporaryArray;
        }
    }

    private void checkIndex(int index, int range) {
        if (index < 0 || index > range) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of available range");
        }
    }
}
