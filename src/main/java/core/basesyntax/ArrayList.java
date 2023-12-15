package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final double GROW_VALUE = 1.5;
    private static final int DEFAULT_SIZE = 10;
    private int size = 0;
    private T[] array;
    private T[] tempArray;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
        tempArray = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " doesn't exist, array size is " + array.length);
        }
        if (size == array.length) {
            grow();
        }
        tempArray = array.clone();
        tempArray[index] = value;
        System.arraycopy(array, index, tempArray, index + 1, size - index);
        size++;
        array = tempArray.clone();
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " doesn't exist, array size is " + array.length);
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " doesn't exist, array size is " + array.length);
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " doesn't exist, array size is " + array.length);
        }
        final T removedItem = (T) array[index];
        size--;
        tempArray = array.clone();
        System.arraycopy(array, index + 1, tempArray, index, size - index);
        array = tempArray.clone();
        return removedItem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if ((array[i] != null && array[i].equals(element)) || array[i] == element) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("There is no element " + element + " in array");
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
        tempArray = (T[]) new Object[(int) (array.length * GROW_VALUE)];
        System.arraycopy(array, 0, tempArray, 0, size);
        array = (T[]) new Object[(int) (array.length * GROW_VALUE)];
        System.arraycopy(tempArray, 0, array, 0, size);
    }
}
