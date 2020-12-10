package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE_COEFFICIENT = 1.5;
    private int size;
    private T[] values;

    public ArrayList() {
        this.values = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public ArrayList(int initialCapacity) {
        this.values = (T[]) new Object[initialCapacity];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            if (size == this.values.length) {
                resize();
            }
            size++;
            values[index] = value;
        } else if (isValidIndex(index) && index <= size - 1) {
            resize();
            size++;
            System.arraycopy(
                    this.values, index, this.values, index + 1, this.values.length - index - 1);
            values[index] = value;
        }
    }

    private void resize() {
        T[] resizedValues = (T[]) new Object[(int) Math.ceil(values.length * RESIZE_COEFFICIENT)];
        System.arraycopy(this.values, 0, resizedValues, 0, this.values.length);
        this.values = resizedValues;
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
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        isValidIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        isValidIndex(index);
        if (index == this.size - 1) {
            size--;
            return (T) values[index];
        } else {
            T item = values[index];
            System.arraycopy(
                    this.values, index + 1, this.values, index, this.values.length - index - 1);
            size--;
            return item;
        }
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < this.size; i++) {
            if (values[i] == null || values[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found.");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private boolean isValidIndex(int index) {
        if (index >= this.size || index < 0) {
            throw new ArrayIndexOutOfBoundsException(
                    "Index " + index + " out of bounds for capacity " + this.values.length);
        }
        return true;
    }
}
