package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Object[] list;
    private int size;

    public ArrayList() {
        list = new Object[10];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size >= list.length) {
            grow(size);
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index is incorrect");
        }
        if (size >= list.length) {
            grow(size);
        }
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
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
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = (T) list[index];
        System.arraycopy(list, index + 1, list, index, size - 1 - index);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == list[i] || element != null && element.equals(list[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }

    private void grow(int size) {
        T[] newArray = (T[]) new Object[size + size / 2];
        System.arraycopy(list, 0, newArray, 0, size);
        list = newArray;
    }
}
