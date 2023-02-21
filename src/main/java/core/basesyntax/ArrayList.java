package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROW_MULTIPLY = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[])new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growCheck();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Element not found at " + index);
        }
        growCheck();
        System.arraycopy(array, index, array,index + 1,size - index);
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
        if (isNotValidValue(index)) {
            throw new ArrayListIndexOutOfBoundsException("Element does not exist");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (isNotValidValue(index)) {
            throw new ArrayListIndexOutOfBoundsException("Element does not exist");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isNotValidValue(index)) {
            throw new ArrayListIndexOutOfBoundsException("Element does not exist in position"
                    + index);
        }
        T tempVal = (T)array[index];
        System.arraycopy(array, index + 1, array, index, --size - index);
        return tempVal;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element
                    || array[i] != null && array[i].equals(element)) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("Element does not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growCheck() {
        if (size == array.length) {
            grow();
        }
    }

    private void grow() {
        T[] arrayCopy = (T[])new Object[DEFAULT_CAPACITY
                + DEFAULT_CAPACITY >> 1];
        System.arraycopy(array, 0, arrayCopy, 0, arrayCopy.length);
        array = arrayCopy;
    }

    private boolean isNotValidValue(int index) {
        return index >= size || index < 0;
    }
}
