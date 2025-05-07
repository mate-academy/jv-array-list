package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private static final double INCREASE_COEFFICIENT = 1.5;
    private Object[] values;
    private int size;

    public ArrayList() {
        values = new Object[INITIAL_SIZE];
    }

    @Override
    public void add(T value) {
        increase();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndexInBounds(index);
        increase();
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
        checkIndexInBounds(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexInBounds(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexInBounds(index);
        final T oldValue = (T) values[index];
        size--;
        System.arraycopy(values, index + 1, values, index, size - index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increase() {
        if (size == values.length) {
            final Object[] buffer = new Object[(int) (values.length * INCREASE_COEFFICIENT)];
            System.arraycopy(values, 0, buffer, 0, values.length);
            values = buffer;
        }
    }

    private void checkIndexInBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
