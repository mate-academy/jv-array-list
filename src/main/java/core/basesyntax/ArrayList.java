package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double SIZE_MULTIPLICATION = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        try {
            if (size == array.length) {
                array = reSize(array);
            }
            array[size] = value;
            size++;
        } catch (NullPointerException e) {
            throw new RuntimeException("Value" + value + " doesn't exist" + e);
        }
    }

    @Override
    public void add(T value, int index) {
        try {
            if (size == array.length) {
                array = reSize(array);
            }
            System.arraycopy(array, index, array, index + 1, size - index + 1);
            array[index] = value;
            size++;
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException("Array index"
                    + index + " if out of bounds exception" + e);
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
        rangeCheck(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index + 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || (array[i] != null && array[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element doesn't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    private T[] reSize(T[] array) {
        T[] reSizeArray = (T[]) new Object[(int) (array.length * SIZE_MULTIPLICATION)];
        System.arraycopy(array, 0, reSizeArray,0, array.length);
        return reSizeArray;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index"
                    + index + " out of bounds exception");
        }
    }
}

