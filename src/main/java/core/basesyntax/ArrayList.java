package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] values;
    private int size = 0;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_SIZE];
    }

    public void resizeArray() {
        int newSize = (size + (size >> 1));
        values = Arrays.copyOf(values, newSize);
    }

    public void checkIndex(int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index doesn't exist");
        }
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            resizeArray();
        }
        values[size] = value;
        size++;

    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("This index doesn't exist");
        }

        if (size == values.length) {
            resizeArray();
        }

        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) {
        for (int h = 0; h < list.size(); h++) {
            add(list.get(h));
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
        T removedValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int l = 0; l <= size; l++) {
            if (Objects.equals(values[l], element)) {
                size--;
                System.arraycopy(values, l + 1, values, l, size - l);
                return element;
            }
        }
        throw new NoSuchElementException("There is no valid value in array");

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
