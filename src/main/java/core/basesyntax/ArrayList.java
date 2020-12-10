package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            grow();
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index <= size && index >= 0) {
            if (size == data.length) {
                grow();
            }
            System.arraycopy(data, index, data, index + 1, size - index);
            data[index] = value;
            size++;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > data.length) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            data[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T value = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - index + 1);
        size--;
        return value;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (data[i] != null && data[i].equals(t) || data[i] == null && t == null) {
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

    private void indexCheck(int i) {
        if (i >= size || i < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void grow() {
        Object[] oldList = data;
        data = new Object[data.length * (data.length / 2)];
        System.arraycopy(oldList, 0, data, 0, oldList.length);
    }
}
