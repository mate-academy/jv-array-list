package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    public static final int DEFAULT_CAPACITY = 10;

    private T[] values = (T[]) new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (!isHaveSpace()) {
            grow();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (!isHaveSpace()) {
            grow();
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
        if (index < size) {
            return values[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void set(T value, int index) {
        if (index < size) {
            values[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        T oldValue = values[index];
        if (size == 0) {
            return oldValue;
        }
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null ? values[i] == t : values[i].equals(t)) {
                remove(i);
                return t;
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

    private boolean isHaveSpace() {
        return values.length > size;
    }

    private void grow() {
        T[] tempValues = (T[]) new Object[values.length + (values.length >> 1)];
        System.arraycopy(values, 0, tempValues, 0, values.length);
        values = tempValues;
    }
}
