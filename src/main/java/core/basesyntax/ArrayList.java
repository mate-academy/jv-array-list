package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final double GROW_RATE = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void copyValues(int start, int end, int newStart) {
        System.arraycopy(values, start, values, newStart, end - start + 1);
    }

    private void checkSizeEnlargeCapacity() {
        if (size == values.length) {
            T[] newValues = (T[]) new Object[(int) (size * GROW_RATE)];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
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
        checkSizeEnlargeCapacity();
        values[size++] = value;
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
            checkSizeEnlargeCapacity();
            copyValues(index, size - 1, index + 1);
            values[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            checkSizeEnlargeCapacity();
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            if (values[index] == null) {
                return null;
            } else {
                return values[index];
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
            result = values[index];
            copyValues(index + 1, --size, index);
            values[size] = null;
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
