package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double INCREASING_RATE = 1.5;
    private static int MAXIMUM_CAPACITY = 10;
    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        array = (T[]) new Object[MAXIMUM_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (isFull()) {
            increaseCapacity();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of Bounds; "
                    + "index: " + index + " size: " + size);
        }
        if (size + 1 >= MAXIMUM_CAPACITY) {
            increaseCapacity();
        }
        @SuppressWarnings("unchecked") T[] partOfArray = (T[]) new Object[size - index + 1];
        System.arraycopy(array, index, partOfArray, 0, size - index + 1);
        array[index] = value;
        System.arraycopy(partOfArray, 0, array, index + 1, partOfArray.length);
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        @SuppressWarnings("unchecked") T[] arrayToAdd = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arrayToAdd[i] = list.get(i);
        }
        if (size + list.size() >= MAXIMUM_CAPACITY) {
            increaseCapacity();
        }
        System.arraycopy(arrayToAdd, 0, array, size, arrayToAdd.length);
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of Bounds; "
                    + "index: " + index + " size: " + size);
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of Bounds; "
                    + "index: " + index + " size: " + size);
        }
        if (size + 1 == MAXIMUM_CAPACITY) {
            increaseCapacity();
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of Bounds; "
                    + "index: " + index + " size: " + size);
        }
        T value = array[index];
        if (index + 1 == MAXIMUM_CAPACITY) {
          array[index] = null;
        } else {
            System.arraycopy(array, index + 1, array, index, size);
        }
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        T value;
        for (int index = 0; index < size; index++) {
            if (array[index] == element
                    || (array[index] != null && array[index].equals(element))) {
                value = array[index];
                System.arraycopy(array, index + 1, array, index, size);
                size--;
                return value;
            }
        }
        throw new NoSuchElementException("No such element in ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == MAXIMUM_CAPACITY;
    }

    private void increaseCapacity() {
        MAXIMUM_CAPACITY = (int)(MAXIMUM_CAPACITY * INCREASING_RATE);
        @SuppressWarnings("unchecked") T[] newArray = (T[]) new Object[MAXIMUM_CAPACITY];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
}
