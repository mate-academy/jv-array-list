package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;
    private static final double GROW = 1.5;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[START_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("IndexOutOfBounds");
        } else {
            if (size == array.length) {
                grow();
            }
            T[] newArray = (T[]) new Object[array.length];
            System.arraycopy(array, 0, newArray,0, index);
            newArray[index] = value;
            System.arraycopy(array, index, newArray,index + 1, size - index);
            array = newArray;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > array.length) {
            grow();
        }
        T[] acceptedArray = (T[]) new Object[list.size()];
        for (int i = 0; i < acceptedArray.length; i++) {
            acceptedArray[i] = list.get(i);
        }
        System.arraycopy(acceptedArray, 0, array, size, acceptedArray.length);
        size += acceptedArray.length;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T result = array[index];
        size--;
        System.arraycopy(array, index + 1, array, index, size - index);
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || array[i] != null && array[i].equals(element)) {
                System.arraycopy(array, i + 1, array, i, size - i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException();
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
        T[] newArray = (T[]) new Object[(int) (array.length * GROW)];
        System.arraycopy(array, 0, newArray,0, size);
        array = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds for ArrayList");
        }
    }
}
