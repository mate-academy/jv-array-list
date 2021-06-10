package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double INCREASING_RATE = 1.5;
    private static int MAXIMUM_CAPACITY = 10;
    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        size = 0;
        array = (T[]) new Object[MAXIMUM_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkSize(size);
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        validateIndex(index);
        checkSize(size + 1);

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int index = 0; index < list.size(); index++) {
            add(list.get(index));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T value = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < size; index++) {
            if (array[index] == element
                    || (array[index] != null && array[index].equals(element))) {
                return remove(index);
            }
        }
        throw new NoSuchElementException("No such element in ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkSize(int size) {
        if (size >= MAXIMUM_CAPACITY) {
            increaseCapacity();
        }
    }

    private void increaseCapacity() {
        MAXIMUM_CAPACITY = (int)(MAXIMUM_CAPACITY * INCREASING_RATE);
        @SuppressWarnings("unchecked") T[] newArray = (T[]) new Object[MAXIMUM_CAPACITY];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private void validateIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of Bounds; "
                    + "index: " + index + " size: " + size);
        }
    }
}
