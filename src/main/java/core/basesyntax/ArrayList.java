package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] list;
    private int size;

    public ArrayList() {
        list = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkAndGrow();
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element to list,"
                    + " index is incorrect " + index);
        }
        if (index == size) {
            add(value);
            return;
        }
        moveForward(index);
        list[index] = value;
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
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T value = list[index];
        moveBack(index);
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (list[i] == element || element != null && element.equals(list[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Couldn't find such an element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        T[] extendedList = (T[]) new Object[list.length + (list.length >> 1)];
        System.arraycopy(list, 0, extendedList, 0, size);
        list = extendedList;
    }

    private void moveBack(int index) {
        System.arraycopy(list, index + 1, list, index, size - index - 1);
        size--;
    }

    private void moveForward(int index) {
        System.arraycopy(list, index, list, index + 1, size - index);
        size++;

    }

    private void checkIndex(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element to list,"
                    + " index is incorrect " + index);
        }
    }

    private void checkAndGrow() {
        if (size >= list.length) {
            grow();
        }
    }
}
