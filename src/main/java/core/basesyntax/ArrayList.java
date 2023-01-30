package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_LENGTH = 10;
    private Object[] array;
    private int currentSize;

    public ArrayList() {
        array = new Object[ARRAY_LENGTH];
        currentSize = 0;
    }

    @Override
    public void add(T value) {
        if (currentSize == array.length) {
            array = Arrays.copyOf(array, currentSize + (currentSize >> 1));
        }
        array[currentSize] = value;
        currentSize++;
    }

    @Override
    public void add(T value, int index) {

    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
