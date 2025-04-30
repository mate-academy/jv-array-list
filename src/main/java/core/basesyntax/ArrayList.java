package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double FACTOR_RESIZE = 1.5;
    private int size;
    private T[] values;

    public ArrayList() {

        values = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }
        growIfArrayFull();
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
        checkIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (element == null ? values[i] == null : element.equals(values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (values.length == size) {
            T[] modifiedArray = (T[]) new Object[(int) (values.length * FACTOR_RESIZE)];
            System.arraycopy(values, 0, modifiedArray, 0, values.length);
            values = modifiedArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || size <= index) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index
                    + ", size: " + size);
        }
    }
}
