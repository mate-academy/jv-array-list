package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double SIZE_MULTIPLICATION = 1.5;
    private int arrayLength = DEFAULT_CAPACITY;
    private T[] array = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    public ArrayList() {
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
            if (size == arrayLength) {
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
        if (list == null) {
            throw new NullPointerException("ArrayList doesn't exist");
        }
        if (list.size() > array.length) {
            array = reSize(array);
        }
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
        System.arraycopy(array,index + 1, array, index, size - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || (array[i] != null && array[i].equals(element))) {
                T removedElement = array[i];
                System.arraycopy(array, i + 1, array, i, size - i);
                size--;
                return removedElement;
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

    @Override
    public T[] reSize(T[] array) {
        arrayLength = (int) (arrayLength * SIZE_MULTIPLICATION);
        T[] reSizeArray = (T[]) new Object[arrayLength];
        System.arraycopy(array,0, reSizeArray,0,array.length);
        return reSizeArray;
    }

    @Override
    public void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index"
                    + index + " out of bounds exception");
        }
    }
}
