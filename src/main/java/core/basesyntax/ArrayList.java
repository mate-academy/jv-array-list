package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private T[] data = (T[]) new Object[INITIAL_SIZE];
    private int size = 0;

    public void grow() {
        if (size == data.length) {
            T[] newData = (T[]) new Object[data.length + (data.length >> 1)];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }

    @Override
    public void add(T value) {
        grow();
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) throws  ArrayListIndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        if (index == size) {
            add(value);
            return;
        }

        grow();

        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            grow();
            data[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T tmp = data[index];
        int step = size - index - 1;
        System.arraycopy(data, index + 1, data, index, step);
        size--;
        return tmp;
    }

    @Override
    public T remove(T element) throws  NoSuchElementException {
        for (int i = 0; i < data.length; i++) {
            if (Objects.equals(data[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " not found");
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