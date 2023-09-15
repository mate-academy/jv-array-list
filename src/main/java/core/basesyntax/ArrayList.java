package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    @Override
    public void add(T value) {
        checkAndExpandArray();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        checkBounds(index, size + 1);
        if (index == size) {
            add(value);
        } else {
            checkAndExpandArray();
            for (int i = size; i > index; i--) {
                values[i] = values[i - 1];
            }
            values[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list.isEmpty()) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        checkBounds(index, size);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        checkBounds(index, size);
        values[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        checkBounds(index, size);
        final T result = (T) values[index];
        for (int i = index; i < size - 1; i++) {
            values[i] = values[i + 1];
        }
        values[--size] = null;
        return result;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(values[i], element)) {
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

    private void checkAndExpandArray() {
        if (values == null) {
            values = (T[]) new Object[DEFAULT_CAPACITY];
        } else if (values.length == size) {
            int newSize = values.length + (values.length >> 1);
            T[] newValues = (T[]) new Object[newSize];
            System.arraycopy(values, 0, newValues, 0, values.length);
            values = newValues;
        }
    }

    private void checkBounds(int index, int availableSize)
            throws ArrayListIndexOutOfBoundsException {
        if (index >= availableSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds, available array size: " + availableSize);
        }
    }
}
