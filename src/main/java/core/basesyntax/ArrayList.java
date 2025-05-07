package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;
    private static final int EMPTY_LIST = 0;
    private static final double COEFFICIENT_MULTIPLY = 1.5;
    private int size;
    private Object[] array;

    public ArrayList() {
        this.size = EMPTY_LIST;
        this.array = new Object[START_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkResize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of range");
        }
        checkResize();
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
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T value = (T) array[index];
        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, array, index, size - index);
        }
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

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of range");
        }
    }

    private void checkResize() {
        if (size + 1 > array.length) {
            array = Arrays.copyOf(array, incrementLength());
        }
    }
}
