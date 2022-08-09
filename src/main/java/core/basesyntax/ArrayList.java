package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;
    private static final double COEFFICIENT_MULTIPLY = 1.5;
    private int size;
    private Object[] array = new Object[START_CAPACITY];

    @Override
    public void add(T value) {
        if (size + 1 > array.length) {
            array = Arrays.copyOf(array, incrementLength());
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of range");
        }
        int newSize = (size + 1 > array.length) ? incrementLength() : array.length;
        Object[] newArray = new Object[newSize];

        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = value;
        System.arraycopy(array, index, newArray, index + 1, size - index);
        array = newArray;
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
        checkArrayListIndexOutOfBoundsException(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkArrayListIndexOutOfBoundsException(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkArrayListIndexOutOfBoundsException(index);
        Object[] newArray = new Object[array.length];
        T value;

        System.arraycopy(array, 0, newArray, 0, index);
        value = (T) array[index];
        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, newArray, index, size - index);
        }
        array = newArray;
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (equals(element, array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Value not found: " + element.toString());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int incrementLength() {
        return (int) (array.length * COEFFICIENT_MULTIPLY);
    }

    private boolean equals(Object first, Object second) {
        return first == second || (first != null && first.equals(second));
    }

    private void checkArrayListIndexOutOfBoundsException(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of range");
        }
    }
}
