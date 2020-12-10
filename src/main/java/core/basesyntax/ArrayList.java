package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private static final double EXPAND_MULTIPLIER = 1.5;
    private T[] collection;

    private int size = 0;

    public ArrayList() {
        this.collection = (T[]) new Object[CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == collection.length) {
            expand();
        }
        collection[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index out of bound");
        }
        if (size == collection.length) {
            expand();
        }
        System.arraycopy(collection, index, collection, index + 1, size - index);
        collection[index] = value;
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
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index out of bound");
        }
        return collection[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index out of bound");
        }
        collection[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index out of bound");
        }
        T removedElement = collection[index];
        System.arraycopy(collection, index + 1, collection, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T t) {
        int index = findIndex(t);
        if (index == -1) {
            throw new NoSuchElementException("No Such Element");
        }
        return remove(index);
    }

    private int findIndex(T t) {
        for (int i = 0; i < size; i++) {
            if ((collection[i] == t) || collection[i] != null && collection[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void expand() {
        T[] listExpanded = (T[]) new Object[(int) (collection.length * EXPAND_MULTIPLIER)];
        System.arraycopy(collection, 0, listExpanded, 0, collection.length);
        collection = listExpanded;
    }
}
