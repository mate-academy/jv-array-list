package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[START_CAPACITY];
    }

    @Override
    public void add(T value) {
        addFreeSpace();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            addFreeSpace();
            T[] newValues = (T[]) new Object[values.length];
            System.arraycopy(values, 0, newValues, 0, index);
            newValues[index] = value;
            System.arraycopy(values, index, newValues, index + 1, size - index);
            size++;
            values = newValues;
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
        T[] newValues = (T[]) new Object[values.length];
        System.arraycopy(values, 0, newValues, 0, index);
        System.arraycopy(values, index + 1, newValues, index + 1 - 1, values.length - (index + 1));
        T result = values[index];
        values = newValues;
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(values[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element: " + element);
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
        int newLength = values.length + (values.length >> 1);
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(values, 0, newArray, 0, values.length);
        values = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index " + index
              + " is out of bound for length " + size);
        }
    }

    private void addFreeSpace() {
        if (values.length == size) {
            grow();
        }
    }
}
