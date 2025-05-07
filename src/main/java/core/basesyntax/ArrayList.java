package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        isNeadToResize();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        isNeadToResize();
        if (index != size) {
            checkIndexWithSameSize(index);
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
        checkIndexWithSameSize(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexWithSameSize(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexWithSameSize(index);
        final T removedElement = values[index];
        System.arraycopy(values, index + 1,
                values, index,
                values.length - index - 1);
        values[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (isEquals(element, values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found in ArrayList");
    }

    private void grow() {
        int newCapacity = (int) (values.length * 1.5);
        T[] newValues = (T[]) new Object[newCapacity];
        System.arraycopy(values, 0, newValues, 0, size);
        values = newValues;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void isNeadToResize() {
        if (size == values.length || (size - 1) == values.length) {
            grow();
        }
    }

    private void checkIndexWithSameSize(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
    }

    private boolean isEquals(T a, T b) {
        return a == null ? a == b : a.equals(b);
    }
}
