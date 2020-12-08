package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private int size = 0;
    private T[] array;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            expandArray();
        }
        array[size] = value;
        size += 1;
    }

    @Override
    public void add(T value, int index) {
        indexValidation(index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexValidation(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        return null;
    }

    @Override
    public T remove(T t) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return array[0] == null;
    }

    private void indexValidation(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index " + index
                    + " is out of the range of list size " + size);
        }
    }

    private void expandArray() {
        array = Arrays.copyOf(array, (int) (array.length * 1.5));
    }
}
