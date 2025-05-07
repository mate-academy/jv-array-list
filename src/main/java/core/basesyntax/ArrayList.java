package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private int size;
    private T[] array;

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Array's capacity cannot be <= 0");
        }
        array = (T[]) new Object[capacity];
    }

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index cannot be < 0 or > size");
        }
        checkCapacity();
        if (size - index >= 0) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
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
        T removedElement = (T) array[index];
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((array[i] == null && element == null)
                    || (array[i] != null && array[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " is not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void validateIndex(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
            "Index cannot be less than 0 (index: " + index + ", size: " + size + ")");
        }
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
            "Index cannot be >= size (index: " + index + ", size: " + size + ")");
        }
    }

    private void checkCapacity() {
        if (size == array.length) {
            int updatedCapacity = (int) (array.length * GROW_FACTOR);
            T[] updatedArray = (T[]) new Object[updatedCapacity];
            System.arraycopy(array, 0, updatedArray, 0, size);
            array = updatedArray;
        }
    }
}
