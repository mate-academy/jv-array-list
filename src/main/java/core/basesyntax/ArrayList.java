package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private Object[] list;
    private int size;

    public ArrayList() {
        list = new Object[CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (list.length == size) {
            list = grow();
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("There is not enough space in the array");
        }
        if (size == list.length) {
            list = grow();
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
        int numMoved = size - index - 1;
        T removedOldValue = (T) list[index];
        System.arraycopy(list, index + 1, list, index, numMoved);
        size--;
        return removedOldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == list[i] || element != null && element.equals(list[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such elements");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        T[] grewArray = (T[]) new Object[size + (size / 2)];
        System.arraycopy(list, 0, grewArray, 0, size);
        return grewArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds of an array");
        }
    }
}
