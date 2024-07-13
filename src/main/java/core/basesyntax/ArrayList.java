package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        array = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, true);
        growIfArrayFull();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
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
        checkIndex(index, false);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, false);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);
        T removedElement = array[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        array[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < size; index++) {
            if (element == array[index]
                    || (element != null && element.equals(array[index]))) {
                return remove(index);
            }
        }
        throw new NoSuchElementException("Element " + element + " is not found in ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void growIfArrayFull() {
        if (size == array.length) {
            int newCapacity = (int) Math.min(array.length * GROWTH_FACTOR, MAX_ARRAY_SIZE);
            if (newCapacity <= array.length) {
                throw new OutOfMemoryError("Required array size too large");
            }
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void checkIndex(int index, boolean forAddOperation) {
        if (forAddOperation) {
            if (index < 0 || index > size) {
                throw new ArrayListIndexOutOfBoundsException("Index " + index
                        + " out of bounds for add operation of size " + size);
            }
        } else {
            if (index < 0 || index >= size) {
                throw new ArrayListIndexOutOfBoundsException("Index " + index
                        + " out of bounds for size " + size);
            }
        }
    }
}
