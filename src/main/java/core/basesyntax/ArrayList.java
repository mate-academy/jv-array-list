package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int SIZE_ARRAY = 10;
    private static final double COEFFICIENT_SIZE_ARRAY = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[SIZE_ARRAY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        widenArray();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        widenArray();
        System.arraycopy(this.values, index, this.values, index + 1, size - index);
        this.values[index] = value;
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
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Houston we have a BUG!");
        }
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Houston we have a BUG!");
        }
        this.values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = (T) values[index];
        System.arraycopy(this.values, index + 1, this.values, index, size - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (this.values[i] == t || t != null && t.equals(this.values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void widenArray() {
        if (values.length == size) {
            T[] newArray = (T[]) new Object[(int) (values.length * COEFFICIENT_SIZE_ARRAY)];
            System.arraycopy(this.values, 0, newArray, 0, size);
            values = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index > this.size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Houston we have a BUG!");
        }
    }
}
