package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    T[] data;
    T[] tempData;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private int size;

    private int newCapacity () {
        return data.length + (data.length >> 1);
    }

    private T[] expandedData() {
        return (T[]) new Object[newCapacity()];
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            tempData = data;
            data = expandedData();
            System.arraycopy(tempData, 0, data, 0, tempData.length);
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == data.length) {
            tempData = data;
            data = expandedData();
            System.arraycopy(tempData, 0, data, 0, tempData.length);
        }
        if (index > size) {
            throw new ArrayListIndexOutOfBoundsException("TEMPORARY Can`t added!!");
        }
        System.arraycopy(data, index, data, index + 1, data.length - index - 1);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        if (index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("TEMPORARY Can`t get value!!");
        }
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("TAMPORARY Can`t set value!!");
        }
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        System.arraycopy(data, index, data, index - 1, size - index);
        return (T) data;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        //if (size !=0) {return true;}
        return size == 0;
    }
    ///////////////////////////DELETE "TO STRING" !!!!!!!////////////////////////
    @Override
    public String toString() {
        return "ArrayList{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
