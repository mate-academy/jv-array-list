package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREMENT_BY_HALF = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        this.values = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            resize();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == values.length) {
            resize();
        }
        if (index == size) {
            add(value);
            return;
        } else if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            System.arraycopy(values, index, values, index + 1, size - index);
            set(value, index);
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
        T result = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[--size] = null;
        return result;
    }

    @Override
    public T remove(T t) {
        int index = -1;
        T value = null;
        for (int i = 0; i < size; i++) {
            if (values[i] == t || (values[i] != null && values[i].equals(t))) {
                index = i;
                value = values[i];
                System.arraycopy(values, index + 1, values, index, size - index);
                size--;
                return value;
            }
        }
        throw new NoSuchElementException("Can't find element with same value");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        T[] newArray = (T[]) new Object[(int) (values.length * INCREMENT_BY_HALF)];
        System.arraycopy(values, 0, newArray, 0, size);
        values = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Incorrect index");
        }
    }
}
