package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkAndResize();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAndResize();
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't correctly add element by index "
                    + index + "!");
        }
        System.arraycopy(data, index, data, index + 1, data.length - (index + 1));
        data[index] = value;
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
        checkIndex(index, "get");
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, "replace");
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, "delete");
        T temp = data[index];
        if (index == 0 || size == data.length || index < size) {
            System.arraycopy(data,index + 1, data, index, size - (index + 1));
            size--;
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
                return remove(i);
            }
        }
        throw new NoSuchElementException("Item not found!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getNewCapacity() {
        return data.length + (data.length >> 1);
    }

    private T[] expandData() {
        return (T[]) new Object[getNewCapacity()];
    }

    private void checkIndex(int index, String action) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't correctly "
                    + action + "  element by index "
                    + index + "!");
        }
    }

    private void checkAndResize() {
        T[] tempData;
        if (size == data.length) {
            tempData = data;
            data = expandData();
            System.arraycopy(tempData, 0, data, 0, tempData.length);
        }
    }
}
