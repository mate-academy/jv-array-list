package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final double DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size = 0;

    public ArrayList() {
        array = (T[]) new Object[(int) DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        grow();
        if (index <= size && index > -1) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException();
        }
    }

    @Override
    public void addAll(List<T> list) {
        grow();
        T[] secondArray = (T[]) new Object[list.size()];
        for (int j = 0; j < list.size(); j++) {
            secondArray[j] = list.get(j);
        }
        System.arraycopy(secondArray, 0, array, size, secondArray.length);
        size += secondArray.length;
    }

    @Override
    public T get(int index) {
        for (int j = 0; j < array.length; j++) {
            if (index >= size || index < 0) {
                throw new ArrayListIndexOutOfBoundsException();
            }
            if (index == j) {
                return array[j];
            }
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || size <= index) {
            throw new ArrayListIndexOutOfBoundsException();
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        grow();
        if (index < 0 || size <= index) {
            throw new ArrayListIndexOutOfBoundsException();
        }
        T res = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
        return res;
    }

    @Override
    public T remove(T element) {
        int check = 0;
        for (int j = 0; j < size - 1; j++) {
            if (Objects.equals(array[j], element)) {
                check = 1;
                System.arraycopy(array, j + 1, array, j, size - j);
                size--;
            }
        }
        if (check != 1) {
            throw new NoSuchElementException();
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public void grow() {
        if (size == array.length) {
            int newCapacity = (int) (size * 1.5);
            T[] nextArray = Arrays.copyOf(array, newCapacity);
            array = nextArray;
        }
    }
}
