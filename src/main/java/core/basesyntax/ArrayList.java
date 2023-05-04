package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private static final double GROW_FACTOR = 1.5;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        changeSize();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        changeSize();
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
        T removedValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(element) || array[i] == element) {
                return remove(i);
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

    private void changeSize() {
        if (array.length == size) {
            T[] newArray = (T[]) new Object[(int) (array.length * GROW_FACTOR)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Input index " + index
                    + "out of bound " + size);
        }
    }

    private void checkAddIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Input index " + index
                    + "out of bound " + size);
        }
    }
}
