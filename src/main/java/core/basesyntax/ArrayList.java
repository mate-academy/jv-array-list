package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_ARRAY_CAPACITY = 10;
    private static final double INCREASING_STEP = 1.5;
    private Object[] values;
    private int size;

    public ArrayList() {
        values = new Object[DEFAULT_ARRAY_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can`t be lass than 0");
        }
        if (index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " is bigger or equal the size ("
                    + size + ")");
        }
        grow();

        Object[] tempValues = new Object[values.length];
        System.arraycopy(values, 0, tempValues, 0, index);
        System.arraycopy(values, index, tempValues, index + 1, size - index);
        tempValues[index] = value;
        values = tempValues;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("Input can`t be null");
        }

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can`t be lass than 0");
        }

        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index
                    + "is bigger or equal than size("
                    + size + ")"
            );
        }
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can`t be lass than 0");
        }

        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index
                            + "is bigger or equal than size("
                            + size + ")"
            );
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can`t be lass than 0");
        }

        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index
                            + "is bigger or equal than size("
                            + size + ")"
            );
        }

        T removedValue = (T) values[index];
        if (index == values.length - 1) {
            values[index] = null;
            size--;
            return removedValue;
        }

        Object[] tempValues = new Object[values.length];
        System.arraycopy(values, 0, tempValues, 0, index);
        System.arraycopy(values, index + 1, tempValues, index, size - index);

        values = tempValues;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(values[i],element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element");
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
        if (values.length == size) {
            int newCapacity = (int) (values.length * INCREASING_STEP);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(values, 0, newArray, 0, size);
            values = newArray;
        }
    }
}
