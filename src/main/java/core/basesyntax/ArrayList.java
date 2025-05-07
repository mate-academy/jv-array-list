package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size == array.length) {
            grow();
        }
        if (index >= 0 && index <= size) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T valueFromIndex = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            return valueFromIndex;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || array[i] != null && array[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " not found in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int newSize = size + (size >> 1);
        array = Arrays.copyOf(array, newSize);
    }
}
