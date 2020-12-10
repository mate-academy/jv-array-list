package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double ARRAY_INCREASE = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public ArrayList(int defaultCapacity) {
        values = (T[]) new Object[defaultCapacity];
        size = 0;
    }

    private T[] letGrow() {
        T[] growLength = (T[]) new Object[(int)Math.ceil(values.length * ARRAY_INCREASE)];
        System.arraycopy(values, 0, growLength, 0, values.length);
        values = growLength;
        return values;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bouns exception");
        }
        if (size == values.length) {
            letGrow();
        }
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
        checkValidIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkValidIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkValidIndex(index);
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
        throw new NoSuchElementException("Can't find element " + t);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkValidIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bouns exception");
        }
    }
}
