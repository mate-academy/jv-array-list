package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final String
            INDEX_OUT_OF_BOUND_MESSAGE = "Index is less than 0 and more than last list index.";
    private static final int INITIAL_CAPACITY = 10;
    private static final double CAPACITY_GROW_COEFFICIENT = 1.5;

    private T[] values;
    private int size;

    ArrayList() {
        this.values = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index <= size && index >= 0) {
            checkSize();
            System.arraycopy(values, index, values, index + 1, size - index);
            values[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUND_MESSAGE);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return values[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUND_MESSAGE);
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            values[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUND_MESSAGE);
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            final T deletedValue = values[index];
            values[index] = null;
            size--;
            System.arraycopy(values, index + 1, values, index, size - index);
            return deletedValue;
        }
        throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUND_MESSAGE);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(values[i], element)) {
                return remove(i);
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

    private void checkSize() {
        if (values.length == size + 1) {
            makeCapacityBigger();
        }
    }

    private void makeCapacityBigger() {
        int newCapacity = (int) (values.length * CAPACITY_GROW_COEFFICIENT);
        T[] newValues = (T[]) new Object[newCapacity];
        System.arraycopy(values, 0, newValues, 0, values.length);
        values = newValues;
    }
}
