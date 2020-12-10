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
            Object[] buffer = new Object[size - index];
            System.arraycopy(data, index, buffer, 0, size() - index);
            size -= size - index;
            if (size + buffer.length > data.length) {
                grow();
            }
            data[index] = value;
            System.arraycopy(buffer, 0, data, index + 1, buffer.length);
            size += buffer.length + 1;
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
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        indexCheck(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) throws ArrayIndexOutOfBoundsException {
        indexCheck(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayIndexOutOfBoundsException {
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
                T value = (T) data[i];
                remove(i);
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
        if (i >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void grow() {
        Object[] oldList = new Object[data.length];
        System.arraycopy(data, 0, oldList, 0, size);
        data = new Object[data.length * (data.length / 2)];
        System.arraycopy(oldList, 0, data, 0, oldList.length);
    }
}
