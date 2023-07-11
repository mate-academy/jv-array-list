package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private T[] values;
    private static final int START_CAPACITY = 10;
    private int size;

    public ArrayList() {
        this.values = (T[]) new Object[START_CAPACITY];
        size = 0;
    }

    private void resizeArray(int newSize) {
        T[] newValues = (T[]) new Object[newSize];
        System.arraycopy(values, 0, newValues, 0, size);
        values = newValues;
    }

    private void resizeArrayIfNeeded() {
        if (size == values.length) {
            int newSize = (int) (values.length * 1.5);
            resizeArray(newSize);
        }
    }

    @Override
    public void add(T value) {
        resizeArrayIfNeeded();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        resizeArrayIfNeeded();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.isEmpty()) {
            return;
        }

        int newSize = size + list.size();
        if (newSize > values.length) {
            resizeArray(newSize);
        }
        for (int i = 0; i < list.size(); i++) {
            values[size + i] = list.get(i);
        }
        size = newSize;

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        T removedValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(values[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
