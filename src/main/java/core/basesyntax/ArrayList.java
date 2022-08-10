package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MIN_CAPACITY = 10;
    private static final double SIZE_MULTIPLIE = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[MIN_CAPACITY];
    }

    @Override
    public void add(T value) {
        increasingSize();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        if (index == size) {
            add(value);
        } else {
            increasingSize();
            System.arraycopy(values, index, values, index + 1, size - index);
            values[index] = value;
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
        T value = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Item not found " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void increasingSize() {
        if (size == values.length) {
            T[] newArray = (T[]) new Object[(int) (values.length * SIZE_MULTIPLIE)];
            System.arraycopy(values, 0, newArray, 0, values.length);
            values = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
