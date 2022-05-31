package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private static final double SIZE_GROWTH = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[INITIAL_SIZE];
    }

    private void grow() {
        if (size == values.length - 1) {
            int newSize = (int) (values.length * SIZE_GROWTH);
            T[] bufferedData = (T[])new Object[newSize];
            System.arraycopy(values, 0, bufferedData, 0, values.length);
            values = bufferedData;
        }
    }

    private void indexChecking(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
    }

    @Override
    public void add(T value) {
        grow();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        grow();
        Object[] bufferedData = new Object[values.length];
        System.arraycopy(values, index, bufferedData, 0, values.length - index);
        values[index] = value;
        System.arraycopy(bufferedData, 0, values, index + 1, values.length - index - 1);
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                break;
            } else {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        indexChecking(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        indexChecking(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        indexChecking(index);
        T oldValue = (T) values[index];
        System.arraycopy(values,
                    index + 1,
                values,
                    index,
                    values.length - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int indexOfElement = Arrays.asList(values).indexOf(element);
        if (indexOfElement > size - 1 || indexOfElement < 0) {
            throw new NoSuchElementException("Index out of range");
        }
        return remove(indexOfElement);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
