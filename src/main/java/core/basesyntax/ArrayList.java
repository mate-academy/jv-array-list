package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_CAPACITY = 10;
    private static final double MULTIPLICATION = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[ARRAY_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index"
                    + index + " out of bounds exception.");
        }
        if (size == array.length) {
            array = resize(array);
        }
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
        System.arraycopy(array, index + 1, array, index, size);
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
        return size == 0;
    }

    private T[] resize(T[] array) {
        T[] reSizeArray = (T[]) new Object[(int) (array.length * MULTIPLICATION)];
        System.arraycopy(array, 0, reSizeArray, 0, array.length);
        return reSizeArray;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index"
                    + index + " out of bounds exception");
        }
    }
}
