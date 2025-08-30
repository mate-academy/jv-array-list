package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROWTH_FACTOR = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] dataArray;
    private int size;

    public ArrayList() {
        dataArray = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= dataArray.length) {
            grow();
        }

        dataArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkPositionIndex(index);

        if (size >= dataArray.length) {
            grow();
        }

        shiftRight(index);

        dataArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > dataArray.length) {
            grow();
        }

        for (int i = 0; i < list.size(); i++) {
            dataArray[size++] = list.get(i);
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

        final T removed = (T) dataArray[index];

        if (index < size - 1) {
            shiftLeft(index);
        }

        dataArray[--size] = null;

        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (equal((T) dataArray[i], element)) {
                final T removed = (T) dataArray[i];

                if (i < size - 1) {
                    shiftLeft(i);
                }

                dataArray[--size] = null;

                return removed;
            }
        }

        throw new NoSuchElementException("No element to remove");
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
        int oldSize = dataArray.length;
        int newSize = (int) (oldSize * GROWTH_FACTOR);

        Object[] newArray = new Object[newSize];

        System.arraycopy(dataArray, 0, newArray, 0, size);

        dataArray = newArray;
    }

    private void shiftRight(int index) {
        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
    }

    private void shiftLeft(int index) {
        System.arraycopy(dataArray, index + 1, dataArray, index, size - index - 1);
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private boolean equal(T firstValue, T secondValue) {
        return firstValue == secondValue || (firstValue != null && firstValue.equals(secondValue));
    }
}
