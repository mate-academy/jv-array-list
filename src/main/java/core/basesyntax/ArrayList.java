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
        grow();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        grow();
        if (index != size) {
            checkIndexWithSameSize(index);
            System.arraycopy(values, index, values, index + 1, size - index);
        }
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] a = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            a[i] = list.get(i);
        }
        int varLength = a.length;
        grow();
        System.arraycopy(a,0,values,size, varLength);
        size += varLength;
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
        values[values.length - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (customEquals(element, values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found in ArrayList");
    }

    private void grow() {
        if (size == values.length) {
            T[] temp = (T[]) new Object[values.length];
            System.arraycopy(values, 0, temp, 0, size);
            values = (T[]) new Object[values.length << 1];
            System.arraycopy(temp, 0, values, 0, size);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexWithSameSize(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
    }

    private boolean customEquals(Object a, Object b) {
        return a == null ? a == b : a.equals(b);
    }
}
