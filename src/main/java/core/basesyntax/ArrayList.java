package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_CAPACITY = 10;
    private int size;
    private T[] data;

    public ArrayList() {
        this.data = (T[]) new Object[ARRAY_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            increaseCapacity();
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            if (size == data.length) {
                increaseCapacity();
            }
            System.arraycopy(data, index, data, index + 1, size - index);
            data[index] = value;
            size++;
        } else {
            throw new ArrayIndexOutOfBoundsException("It is not possible to add an item with "
                    + "this index. Please choose index from: " + 0 + " to: " + size);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        chekIsIndexExist(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        chekIsIndexExist(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        chekIsIndexExist(index);
        T removedValueByIndex = data[index];
        System.arraycopy(data, index + 1, data, index,size - index - 1);
        size--;
        return removedValueByIndex;
    }

    @Override
    public T remove(T t) {
        int indexOfRemovedValue = Arrays.asList(data).indexOf(t);
        if (indexOfRemovedValue < 0) {
            throw new NoSuchElementException("Value not finded! Try another value!");
        }
        return remove(indexOfRemovedValue);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseCapacity() {
        T[] newData = (T[]) new Object[(int) (data.length * 1.5)];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    private void chekIsIndexExist(int index) {
        if (size <= index || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index not exist."
                    + " Please choose index from: " + 0 + " to: " + (size - 1));
        }
    }

}
