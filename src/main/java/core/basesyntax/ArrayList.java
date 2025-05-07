package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MULTIPLICATION_CAPACITY = 1.5;
    private static final String EXCEPTION_MESSAGE = "Index of the bounds. Check your input index";
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfNeeded();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexOnRange(index);
        growIfNeeded();
        if (size - index >= 0) {
            System.arraycopy(values, index, values, index + 1, size - index);
        }
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
        indexOnRangeWithEqualsSize(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        indexOnRangeWithEqualsSize(index);
        T oldValue = values[index];
        values[index] = value;
    }

    @Override
    public T remove(T value) {
        for (int i = 0; i < size; i++) {
            if ((values[i] == value)
                    || (values[i] != null && values[i].equals(value))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public T remove(int index) {
        indexOnRangeWithEqualsSize(index);
        T removed = values[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(values, index + 1, values, index, size - 1 - index);
        }
        T newRemoved = removed;
        values[size - 1] = null;
        size--;
        return newRemoved;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    private void indexOnRange(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(EXCEPTION_MESSAGE);
        }
    }

    private void indexOnRangeWithEqualsSize(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(EXCEPTION_MESSAGE);
        }
    }

    private void growIfNeeded() {
        if (size == values.length) {
            T[] newValues = (T[]) new Object[(int) (values.length * MULTIPLICATION_CAPACITY)];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }
}
