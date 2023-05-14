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
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        verifyIndexForAdd(index);
        growIfLengthEqualsSize();
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
        System.arraycopy(array, index + 1, array, index,size - index - 1);
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
        throw new NoSuchElementException("there is no such element in this array " + element);
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
        if (size == array.length) {
            int newLength = (int) (array.length * MULTIPLIER);
            Object[] newArray = new Object[newLength];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = (T[]) newArray;
        }
    }

    private void verifyIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("wrong index input " + index);
        }
    }

    private void verifyIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("wrong index input " + index);
        }
    }
}
