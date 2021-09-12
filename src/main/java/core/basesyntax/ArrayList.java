package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] values;
    private int size;
    private int capacity = DEFAULT_CAPACITY;

    public ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds!");
        }
        resize();
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds!");
        }
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds!");
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds!");
        }
        Object removedValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return (T) removedValue;
    }

    @Override
    public T remove(T element) {
        T removedValue = null;
        boolean isFind = false;
        for (int i = 0; i < size; i++) {
            if (values[i] != null && ((T) values[i]).equals(element) || values[i] == element) {
                removedValue = remove(i);
                isFind = true;
                break;
            }
        }
        if (!isFind) {
            throw new NoSuchElementException();
        }
        return removedValue;
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
        if (size >= capacity) {
            capacity += capacity / 2;
            Object[] newValues = new Object[capacity];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }
}
