package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIALIZATION_SIZE = 10;
    private static final double ARRAY_RATIO = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[INITIALIZATION_SIZE];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size);
        T[] newValues = (T[]) new Object[values.length + 1];
        if (size > 0) {
            System.arraycopy(values, 0, newValues, 0, values.length);
            System.arraycopy(values, index, newValues, index + 1, size - index);
        }
        newValues[index] = value;
        values = newValues;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        growIfArrayFull();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, size - 1);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index,size - 1);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size - 1);
        T[] newValues = (T[]) new Object[values.length - 1];
        System.arraycopy(values, 0, newValues, 0, index);
        System.arraycopy(values, index + 1, newValues, index, values.length - index - 1);
        T removedElement = values[index];
        values = newValues;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                return remove(i);
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

    private void growIfArrayFull() {
        if (values.length == size) {
            System.arraycopy(values, 0,
                    values = (T[]) new Object[(int) (size * ARRAY_RATIO)], 0, size);
        }
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }
}
