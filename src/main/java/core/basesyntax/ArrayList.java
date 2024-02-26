package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private int size;
    private T[] dataArray;

    public ArrayList() {
        this.dataArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        dataArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        addIndexCheck(index);
        ensureCapacity();
        System.arraycopy(dataArray, index, dataArray, index + 1, dataArray.length - index - 1);
        dataArray[index] = value;
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
        boundsCheck(index);
        return dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        boundsCheck(index);
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        boundsCheck(index);
        final T oldValue = dataArray[index];
        System.arraycopy(dataArray, index + 1, dataArray, index, dataArray.length - index - 1);
        dataArray[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (dataArray[i] == element || (dataArray[i] != null && dataArray[i].equals(element))) {
                final T oldValue = dataArray[i];
                System.arraycopy(dataArray, i + 1, dataArray, i, dataArray.length - i - 1);
                dataArray[--size] = null;
                return oldValue;
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
        return size == 0;
    }

    private void boundsCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(getOutOfBoundsMessage(index));
        }
    }

    private void addIndexCheck(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(getOutOfBoundsMessage(index));
        }
    }

    private void ensureCapacity() {
        if (size == dataArray.length) {
            grow(size + 1);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = dataArray.length;
        int newCapacity = (int) (oldCapacity * GROW_FACTOR);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        T[] newDataArray = (T[]) new Object[newCapacity];
        System.arraycopy(dataArray, 0, newDataArray, 0, dataArray.length);
        dataArray = newDataArray;
    }

    private String getOutOfBoundsMessage(int index) {
        return "Index: " + index + ", Size: " + size;
    }
}
