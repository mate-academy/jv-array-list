package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MULTIPLIER = 1.5;
    private Object[] values;
    private int size;

    public ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            grow();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndex(index);
        }
        if (index == values.length || size == values.length) {
            grow();
        }
        System.arraycopy(values, index,
                values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while ((list.size() + size) >= values.length) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        if (index == size - 1) {
            grow();
        }
        Object value = values[index];
        System.arraycopy(values, index + 1,
                values, index, size - index);
        size--;
        return (T) value;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < values.length; i++) {
            if ((element == null && values[i] == null)
                    || element != null && (element.equals(values[i]))) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Element does not exist");
        }
        return remove(index);
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
        Object[] oldValues = values;
        values = new Object[(int) (oldValues.length * MULTIPLIER)];
        System.arraycopy(oldValues, 0, values, 0, oldValues.length);
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Passed index is invalid");
        }
    }
}
