package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

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
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("TEMPORARY Can`t added!!");
        }
        System.arraycopy(data, index, data, index + 1, data.length - index - 1);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            data[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("TEMPORARY Can`t get value!!");
        }
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("TEMPORARY Can`t set value!!");
        }
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("TEMPORARY Can`t remove!!");
        }
        T temp = data[index];
        if (index == 0) {
            System.arraycopy(data, 1, data, 0, size);
            size--;
            return temp;
        }
        if (size == data.length) {
            System.arraycopy(data, index + 1, data, index, data.length - 1 - index);
            size--;
            data[size] = null;
            return temp;
        }
        if (index < size) {
            System.arraycopy(data, index + 1, data, index, size - index);
            size--;
            data[size] = null;
        }
        return temp;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            size--;
            return null;
        }
        for (int i = 0; i < size; i++) {
            if (data[i] != null && data[i].equals(element)) {
                T temp = data[i];
                System.arraycopy(data, i + 1, data, i, size - 1 - i);
                size--;
                data[size] = null;
                return temp;
            }
        }
        for (T t : data) {
            if (t != null && !t.equals(element)) {
                throw new NoSuchElementException();
            }
        }
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
