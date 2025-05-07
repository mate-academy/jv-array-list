package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private static final double MULTIPLIER = 1.5;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        growIfLengthEqualsSize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        growIfLengthEqualsSize();
        verifyIndexForAdd(index);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        growIfLengthEqualsSize();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        verifyIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        verifyIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        verifyIndex(index);
        final T removedObject = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element || (array[i] != null && array[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("rewrite the element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfLengthEqualsSize() {
        if (array.length == size) {
            int newLength = (int) (array.length * MULTIPLIER);
            Object[] newArray = (T[]) new Object[newLength];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = (T[]) newArray;
        }
    }

    private void verifyIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("rewrite the index");
        }
    }

    private void verifyIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("rewrite the index");
        }
    }
}
