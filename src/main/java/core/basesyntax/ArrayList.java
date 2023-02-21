package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        values = (T[]) new Object[initCapacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size + 1);
        resizeIfNeeded();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
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
        checkIndex(index, size);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T removedValue = (T) values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (compare(values[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeIfNeeded() {
        if (values.length == size) {
            Object[] newArray = new Object[values.length + (values.length >> 2)];
            System.arraycopy(values, 0, newArray, 0, size);
            values = (T[]) newArray;
        }
    }

    private boolean compare(T a, T b) {
        return a == b || (a != null) && Objects.equals(a, b);
    }

    public void checkIndex(int index, int size) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index exceeds ArrayList size");
        }
    }
}
