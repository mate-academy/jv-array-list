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

    private void resizing() {
        T[] newArray = (T[]) new Object[(int) (values.length * INCREMENT_BY_HALF)];
        System.arraycopy(values,0, newArray, 0, size);
        values = newArray;
    }

    private T[] toArray(List<T> list) {
        T[] array = (T[]) new Object[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            resizing();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == values.length) {
            resizing();
        }
        System.arraycopy(values, index, values, index + 1, size - index);
        if (index == size) {
            add(value);
        } else {
            set(value, index);
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        System.arraycopy(toArray(list), 0, values, size, list.size());
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Incorrect index");
        }
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Incorrect index");
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Incorrect index");
        }
        T result = values[index];
        System.arraycopy(values, index + 1, values, index, size - index);
        values[--size] = null;
        return result;
    }

    @Override
    public T remove(T t) {
        int index = -1;
        T value = null;
        for (int i = 0; i < size; i++) {
            if (values[i] == t || values[i].equals(t)) {
                index = i;
                value = values[i];
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Can't find element with same value");
        }
        System.arraycopy(values, index + 1, values, index, size - index);
        size--;
        return value;
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
