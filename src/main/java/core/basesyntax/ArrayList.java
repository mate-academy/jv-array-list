package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;
    private static final float GROW_VALUE = 1.5f;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[])new Object[START_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        values[size] = value;
        size++;
        grow();
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index must be less then size!");
        } else if (index == size) {
            add(value);
        } else {
            T oldValue = values[index];
            values[index] = value;
            add(oldValue, index + 1);
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index must be less then size!");
        }
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index must be less then size!");
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index must be less then size!");
        }
        size--;
        T removedValue = values[index];
        for (int i = index; i <= size; i++) {
            values[i] = values[i + 1];
        }
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

    private void grow() {
        if (size == values.length) {
            int newSize = (int)(GROW_VALUE * values.length);
            T[] newValues = (T[])new Object[newSize];
            for (int i = 0; i < values.length; i++) {
                newValues[i] = values[i];
            }
            values = newValues;
        }
    }
}
