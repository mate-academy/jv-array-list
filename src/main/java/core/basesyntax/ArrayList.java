package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int START_VALUE = 10;
    private int size;
    private Object [] arrayData;

    public ArrayList() {
        this.arrayData = new Object[START_VALUE];
    }

    @Override
    public void add(T value) {
        if (size == arrayData.length) {
            arrayData = resizeArray();
        }
        arrayData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != 0) {
            range(index - 1);
        }
        if (size == arrayData.length) {
            arrayData = resizeArray();
        }
        System.arraycopy(arrayData, index,
                arrayData, index + 1,
                size - index);
        arrayData[index] = value;
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
        range(index);
        return (T) arrayData[index];
    }

    @Override
    public void set(T value, int index) {
        range(index);
        arrayData[index] = value;
    }

    @Override
    public T remove(int index) {
        range(index);
        T element = (T) arrayData[index];
        System.arraycopy(arrayData, index + 1,
                arrayData, index,
                size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arrayData[i], element)) {
                System.arraycopy(arrayData, i + 1,
                        arrayData, i,size - i - 1);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("There is no such element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] resizeArray() {
        int oldCap = arrayData.length;
        if (oldCap > 0) {
            int newCap = oldCap + oldCap / 2;
            arrayData = Arrays.copyOf(arrayData, newCap);
        }
        return arrayData;
    }

    private void range(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index :" + index);
        }
    }
}

