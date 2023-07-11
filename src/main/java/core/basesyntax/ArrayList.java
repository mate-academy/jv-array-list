package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWING_COEFFICIENT = 1.5d;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayTooSmall();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkArrayIndex(index);
            growIfArrayTooSmall();
            System.arraycopy(array, index, array,index + 1,size - index);
            array[index] = value;
            size++;
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
        checkArrayIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkArrayIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkArrayIndex(index);
        T temp = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || array[i] != null && array[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayTooSmall() {
        boolean isSmall = false;
        int capacity = DEFAULT_CAPACITY;
        while (size >= capacity) {
            capacity = (int) (capacity * GROWING_COEFFICIENT);
            isSmall = true;
        }
        if (isSmall) {
            T[] arrayCopy = array.clone();
            array = (T[])new Object[capacity];
            System.arraycopy(arrayCopy, 0, array, 0, arrayCopy.length);
        }
    }

    private void checkArrayIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }
}
