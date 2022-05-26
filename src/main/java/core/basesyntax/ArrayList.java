package core.basesyntax;

import java.util.NoSuchElementException;

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
            checkIndex(index - 1);
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
        checkIndex(index);
        return (T) arrayData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
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
            if (arrayData[i] == element || (arrayData[i] != null && arrayData[i].equals(element))) {
                return remove(i);
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
        int newCap = oldCap + oldCap / 2;
        Object[] arrayTemp = new Object[newCap];
        System.arraycopy(arrayData, 0, arrayTemp, 0, oldCap);
        return arrayTemp;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index :" + index);
        }
    }
}

