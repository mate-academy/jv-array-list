package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ELEMENTS = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_ELEMENTS];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (array.length == size) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Incorrect index");
        }
        if (array.length == size) {
            grow();
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
        indexCheck(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T returnedValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return returnedValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (array[i] == t || array[i] != null && array[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Incorrect value");
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
        int newLength = array.length + (array.length >> 1);
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Incorrect index");
        }
    }
}
