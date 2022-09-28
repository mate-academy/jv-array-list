package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int mSize;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (values.length == mSize) {
            resize();
        }
        values[mSize] = value;
        mSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index > mSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + mSize);
        }
        if (values.length == mSize) {
            resize();
        }
        System.arraycopy(values, index, values, index + 1, mSize - index);
        values[index] = value;
        mSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T value = values[index];
        mSize--;
        System.arraycopy(values, index + 1, values, index, mSize - index);
        return value;
    }

    @Override
    public T remove(T element) {
        T result;
        for (int i = 0; i < mSize; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                result = values[i];
                remove(i);
                return result;
            }
        }
        throw new NoSuchElementException("There is no such element "
                + "<" + element + ">" + " present in ArrayList");
    }

    @Override
    public int size() {
        return mSize;
    }

    @Override
    public boolean isEmpty() {
        return mSize == 0;
    }

    public void resize() {
        T[] data = values;
        values = (T[]) new Object[mSize + (mSize >> 1)];
        System.arraycopy(data, 0, values, 0, mSize);
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= mSize) {
            throw new ArrayListIndexOutOfBoundsException("Index"
                    + index + " out of bounds for length" + mSize);
        }
    }
}
