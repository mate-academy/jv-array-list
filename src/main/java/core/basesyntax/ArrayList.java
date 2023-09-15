package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] values;
    private int size = 0;

    @Override
    public void add(T value) {
        checkAndExpandArray();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        } else if (index == size) {
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
        checkAndExpandArray(values.length + list.size());
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        final T result = (T) values[index];
        for (int i = index; i < size - 1; i++) {
            values[i] = values[i + 1];
        }
        size--;
        values[size] = null;
        return result;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(values[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
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
            values = new Object[DEFAULT_CAPACITY];
        } else if (values.length == size) {
            values = Arrays.copyOf(values, values.length + (values.length >> 1));
        }
    }

    private void checkAndExpandArray(int newSize) {
        if (values == null) {
            values = new Object[DEFAULT_CAPACITY];
        } else if (values.length == size) {
            int currentSize = values.length;
            while (currentSize < newSize) {
                currentSize = currentSize + currentSize >> 1;
            }
            values = Arrays.copyOf(values, currentSize);
        }
    }
}
