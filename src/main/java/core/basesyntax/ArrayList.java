package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (values.length == size) {
            values = grow();
        }
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            checkIndexInBounds(index);
        }
        if (values.length == size + 1) {
            values = grow();
        }
        if (index < values.length && index < size) {
            System.arraycopy(values, index, values, index + 1, size + 1);
            values[index] = value;
            size++;
            return;
        }
        values[size] = value;
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
        if (index <= size || index < 0) {
            checkIndexInBounds(index);
        }
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            checkIndexInBounds(index);
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            checkIndexInBounds(index);
        }
        if (index < (size - 1)) {
            T copy = values[index];
            System.arraycopy(values, index + 1, values, index, size);
            values[--size] = null;
            return copy;
        }
        T copy = values[index];
        values[--size] = null;
        return copy;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(
                "Given element does not exist! Try to write correct element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        if ((size + 1) == values.length) {
            values = resize();
        } else if (size == values.length) {
            values = resize();
        }
        return values;
    }

    private T[] resize() {
        T[] copy = values;
        values = (T[]) new Object[(int) (size * GROW_FACTOR)];
        System.arraycopy(copy, 0, values, 0, size);
        return values;
    }

    private T checkIndexInBounds(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "index cannot be negative! For given array, index should be from "
                            + 0 + " to " + (size - 1));
        }
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    index + " does not exist! For given array, index should be from "
                            + 0 + " to " + (size - 1));
        }
        return null;
    }
}
