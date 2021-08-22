package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final String
            INDEX_OUT_OF_BOUND_MESSAGE = "Index is less than 0 and more than last list index.";
    private static final String COMMA = ",";
    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET = "]";
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_GROW_COEFFICIENT = 1.5;

    private T[] values;
    private int size;
    private int capacity;

    ArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        this.values = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (capacity == size + 1) {
            makeCapacityBigger();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index <= size && index >= 0) {
            if (capacity == size + 1) {
                makeCapacityBigger();
            }
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
        int removedElementIndex;
        boolean isFound = false;
        T deletedValue = null;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(values[i], element)) {
                isFound = true;
                removedElementIndex = i;
                deletedValue = values[i];
                if (size - 1 - removedElementIndex >= 0) {
                    System.arraycopy(
                            values,
                            removedElementIndex + 1,
                            values,
                            removedElementIndex,
                            size - 1 - removedElementIndex
                    );
                }
                break;
            }
        }
        if (isFound) {
            values[size - 1] = null;
            size--;
        } else {
            throw new NoSuchElementException();
        }
        return deletedValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void makeCapacityBigger() {
        capacity = (int) (capacity * CAPACITY_GROW_COEFFICIENT);
        T[] newValues = (T[]) new Object[capacity];
        System.arraycopy(values, 0, newValues, 0, values.length);
        values = newValues;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(LEFT_BRACKET);
        int counter = 0;
        for (int i = 0; i < size; i++) {
            res.append(++counter == size ? values[i] : values[i] + COMMA);
        }
        return res.append(RIGHT_BRACKET).toString();
    }
}
