package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] values;

    public ArrayList() {
        this.values = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size >= values.length) {
            grow();
        }
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + ", Size: " + size);
        }
        if (size == values.length) {
            grow();
        }
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
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexInBounds(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexInBounds(index);
        T oldValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] == element
                    || values[i] != null && values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexInBounds(int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + ", Size: " + size);
        }
    }

    private void grow() {
        T[] tmp = (T[]) new Object[values.length];
        System.arraycopy(values, 0, tmp, 0, tmp.length);
        values = (T[]) new Object[(int) (values.length * 1.5)];
        System.arraycopy(tmp, 0, values, 0, tmp.length);
    }
}
