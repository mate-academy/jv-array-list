package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final int CODE_ADD_COPY = 1;
    private static final int CODE_MINUS_COPY = -1;

    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkSize();
        values[size] = value;
        size++;
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
        int newSize = size - 1;
        System.arraycopy(values, index + 1, values, index, newSize - index);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
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

    private T[] grow() {
        int oldCapacity = values.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        return values = Arrays.copyOf(values, newCapacity);
    }

}
