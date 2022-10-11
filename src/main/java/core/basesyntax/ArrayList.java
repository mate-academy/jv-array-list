package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] dataArray;

    public ArrayList() {
        dataArray = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (dataArray.length == size) {
            growLength();
        }
        dataArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        size++;
        checkIndex(index);
        if (dataArray.length == size) {
            growLength();
        }
        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
        dataArray[index] = value;
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
        return (T) dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T element = (T) dataArray[index];
        size--;
        System.arraycopy(dataArray, index + 1, dataArray, index, size - index);
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, dataArray[i])) {
                T value = (T) dataArray[i];
                remove(i);
                return value;
            }
        }
        throw new NoSuchElementException("Can`t find element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growLength() {
        int newLength = (dataArray.length + (dataArray.length >> 1));
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(dataArray, 0, newArray, 0, size);
        dataArray = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Element is`nt exist by index " + index);
        }
    }
}
