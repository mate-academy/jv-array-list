package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;

    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkSize();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexRangeForAdd(index);
        if (index == size) {
            add(value);
        } else {
            checkSize();
            System.arraycopy(values, index, values, index + 1, size - index);
            values[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexRange(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexRange(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexRange(index);
        T oldValue = values[index];
        size--;
        System.arraycopy(values, index + 1, values, index, size - index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null && element.equals(values[i]) || element == values[i]) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Unable to delete. No value found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexRangeForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %s out of bounds", index));
        }
    }

    private void checkIndexRange(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %s out of bounds", index));
        }
    }

    private void checkSize() {
        if (size == values.length) {
            grow();
        }
    }

    private void grow() {
        int oldCapacity = values.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        T[] oldValues = values;
        values = (T[]) new Object[newCapacity];
        System.arraycopy(oldValues, 0, values, 0, size);
    }

}
