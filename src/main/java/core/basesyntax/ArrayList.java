package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final double DEFAULT_GROW_RATE = 1.5;
    private Object[] values;
    private int size;

    public ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void copyValues(int start, int end, int newStart) {
        Object[] copy = new Object[end - start + 1];
        System.arraycopy(values, start, copy, 0, copy.length);
        System.arraycopy(copy, 0, values, newStart, copy.length);
    }

    private void checkSizeEnlargeCapacity(int sizeIncrement) {
        if (size + sizeIncrement > values.length) {
            int newSize = values.length;
            while (newSize < size + sizeIncrement) {
                newSize = (int) (newSize * DEFAULT_GROW_RATE);
            }
            values = Arrays.copyOf(values,
                    (int) (this.values.length * DEFAULT_GROW_RATE));
        }
    }

    private int indexOf(T element) {
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (values[i] == null && element == null
                    || values[i] != null && values[i].equals(element)) {
                return i;
            }
        }
        return result;
    }

    @Override
    public void add(T value) {
        checkSizeEnlargeCapacity(1);
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size + 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d out of bounds for length %d", index, size));
        }
        if (index == size) {
            add(value);
        } else {
            checkSizeEnlargeCapacity(1);
            copyValues(index, size - 1, index + 1);
            values[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        checkSizeEnlargeCapacity(list.size());
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            if (values[index] == null) {
                return null;
            } else {
                return (T) values[index];
            }
        } else {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d out of bounds for length %d", index, size));
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            values[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d out of bounds for length %d", index, size));
        }
    }

    @Override
    public T remove(int index) {
        T result;
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d out of bounds for length %d", index, size));
        } else {
            result = (T) values[index];
            copyValues(index + 1, size - 1, index);
            values[size - 1] = null;
            size--;
        }
        return result;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException(
                    String.format("Can't find element %s in ArrayList", element));
        }
        return remove(index);
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
