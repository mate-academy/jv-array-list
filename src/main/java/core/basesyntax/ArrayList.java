package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] array;
    private int size;
    private int maxSize;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
        size = 0;
        maxSize = DEFAULT_SIZE;
    }

    @Override
    public void add(T value) {
        if (size >= maxSize) {
            array = grow(array);
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size >= maxSize) {
            array = grow(array);
        }
        if (checkIndex(index)) {
            T[] lastPart = (T[]) new Object[size - index];
            System.arraycopy(array, index, lastPart, 0, lastPart.length);
            array[index] = value;
            size++;
            System.arraycopy(lastPart, 0, array, index + 1, lastPart.length);
            return;
        } else if (index == size) {
            add(value);
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Index out of bounds of ArrayList.");
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > maxSize) {
            array = grow(array);
        }
        for (int i = size; i < size + list.size(); i++) {
            array[i] = list.get(i - size);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (checkIndex(index)) {
            return array[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index out of bounds of ArrayList.");
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            array[index] = value;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Index out of bounds of ArrayList.");
    }

    @Override
    public T remove(int index) {
        if (checkIndex(index)) {
            size--;
            T removed = array[index];
            System.arraycopy(array, index + 1, array, index, size - index);
            array[size] = null;
            return removed;
        }
        throw new ArrayListIndexOutOfBoundsException("Index out of bounds of ArrayList.");
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                index = i;
                break;
            }
        }
        if (index > -1) {
            return remove(index);
        }
        throw new NoSuchElementException("There is no such element in array.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow(T[] array) {
        int oldSize = maxSize;
        maxSize = maxSize + (maxSize / 2);
        T[] newArray = (T[]) new Object[maxSize];
        System.arraycopy(array, 0, newArray, 0, oldSize);
        return newArray;
    }

    private boolean checkIndex(int index) {
        return index < size && index >= 0 ? true : false;
    }
}
