package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        if (size < array.length) {
            array[size] = value;
            size++;
        } else {
            array = grow(array);
            array[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < size && index >= 0) {
            if (size == array.length) {
                array = grow(array);
            }
            System.arraycopy(array, index, array, index + 1, array.length - index - 1);
            array[index] = value;
            size++;
        } else if (index == size) {
            this.add(value);
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() + size > array.length) {
            array = grow(array);
        }
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        for (int i = 0; i < size; i++) {
            if (i == index) {
                array[i] = value;
                return;
            }
        }
        throw new ArrayListIndexOutOfBoundsException("Index " + index
                + " out of bounds for length " + size);
    }

    @Override
    public T remove(int index) {
        T value = null;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                value = array[i];
                array[i] = null;
                for (int j = i + 1; j < size; j++) {
                    array[j - 1] = array[j];
                }
                array[size - 1] = null;
                size--;
                return value;

            }
        }
        throw new ArrayListIndexOutOfBoundsException("Index " + index
                + " out of bounds for length " + size);
    }

    @Override
    public T remove(T element) {
        int removedIndex = -1;
        T value = null;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], element)) {
                value = array[i];
                this.remove(i);
                return value;
            }
        }
        throw new NoSuchElementException("There is no such element in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow(T[] originalArray) {
        int newSize = (int) Math.ceil(originalArray.length * 1.5);
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(originalArray, 0, newArray, 0, originalArray.length);
        return newArray;
    }
}
