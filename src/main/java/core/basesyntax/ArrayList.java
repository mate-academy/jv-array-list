package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE_COEFFICIENT = 1.5;
    private int size;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public ArrayList(int initialCapacity) {
        values = (T[]) new Object[initialCapacity];
        size = 0;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (size == values.length) {
            resize();
        }
        if (index != size) {
            isValidIndex(index);
            System.arraycopy(
                    values, index, values, index + 1, size - index);
        }
        values[index] = value;
        size++;
    }

    private void resize() {
        T[] resizedValues = (T[]) new Object[(int) Math.ceil(values.length * RESIZE_COEFFICIENT)];
        System.arraycopy(values, 0, resizedValues, 0, values.length);
        values = resizedValues;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isValidIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        isValidIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        isValidIndex(index);
        T item = values[index];
        System.arraycopy(
                values, index + 1, values, index, size - index - 1);
        size--;
        return item;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if ((t == null && values[i] == null) || (values[i] != null && values[i].equals(t))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void isValidIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException(
                    "Index " + index + " out of bounds for size " + size);
        }
    }
}
