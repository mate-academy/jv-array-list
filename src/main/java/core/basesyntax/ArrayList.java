package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;
    private static final float GROW_MULTIPLIER = 1.5f;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[])new Object[START_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index must be less then size!");
        }
        if (index == size) {
            add(value);
        } else {
            ensureCapacity();
            System.arraycopy(values, index, values, index + 1, size - index);
            values[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new RuntimeException("Can't add list when it's null!");
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkBounds(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkBounds(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBounds(index);
        T removedValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - 1 - index);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] == element
                    || values[i] != null && values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element presents there!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size + 1 == values.length) {
            int newSize = (int)(GROW_MULTIPLIER * values.length);
            T[] newValues = (T[])new Object[newSize];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }

    private void checkBounds(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index must be less then size!");
        }
    }
}
