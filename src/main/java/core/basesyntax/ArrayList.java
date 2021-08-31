package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_RATE = 1.5;
    private static final String EXEPTION_MASAGE = "Invalid index";
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        capacityCheck();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        capacityCheck();
        if (index == size) {
            add(value);
        } else {
            isIndexValid(index);
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
        isIndexValid(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexValid(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexValid(index);
        T removedElement;
        removedElement = values[index];
        System.arraycopy(values, index + 1, values, index, size - 1 - index);
        values[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] == element
                    || (values[i] != null
                    && values[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(EXEPTION_MASAGE);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void capacityCheck() {
        if (size >= values.length) {
            T[] tempArray = (T[]) new Object[(int) (values.length * GROWTH_RATE)];
            System.arraycopy(values, 0, tempArray, 0, values.length);
            values = tempArray;
        }
    }

    private void isIndexValid(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(EXEPTION_MASAGE);
        }
    }
}
