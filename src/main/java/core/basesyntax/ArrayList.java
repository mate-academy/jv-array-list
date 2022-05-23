package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    public static final int LOWER_BOUND = 0;
    public static final int MIN_ARRAYS_LENGTH = 10;
    private T[] arrays = (T[]) new Object[MIN_ARRAYS_LENGTH];
    private int size;

    @Override
    public void add(T value) {
        sizeValidate();
        arrays[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexValidate(index, size + 1);
        sizeValidate();
        System.arraycopy(arrays, index, arrays, index + 1, size - index);
        arrays[index] = value;
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
        indexValidate(index, size - 1);
        return arrays[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidate(index, size - 1);
        arrays[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidate(index, size - 1);
        T removedValue = arrays[index];
        System.arraycopy(arrays, index + 1, arrays, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arrays[i], element)) {
                return remove(i);
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

    private void sizeValidate() {
        if (size == arrays.length) {
            grow();
        }
    }

    private void grow() {
        int newArraysLength = (int) (arrays.length * 1.5);
        arrays = Arrays.copyOf(arrays, newArraysLength);
    }

    private void indexValidate(int index, int upperBound)
            throws ArrayListIndexOutOfBoundsException {
        if (index < LOWER_BOUND || index > upperBound) {
            throw new ArrayListIndexOutOfBoundsException("Not valid index");
        }
    }
}
