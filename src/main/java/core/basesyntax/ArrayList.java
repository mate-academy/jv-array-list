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
    public void add(T value, int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index out of bound");
        }
        if (size == collection.length) {
            expand();
        }
        T[] listAdd = (T[]) new Object[(size + 1)];
        for (int i = 0; i < listAdd.length; i++) {
            if (i < index) {
                listAdd[i] = collection[i];
            }
            if (i == index) {
                listAdd[i] = value;
            }
            if (i > index) {
                listAdd[i] = collection[i - 1];
            }
        }
        collection = listAdd;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] newList = (T[]) new Object[(list.size() + size)];
        for (int i = 0; i < size; i++) {
            newList[i] = collection[i];
        }
        for (int i = 0; i < list.size(); i++) {
            newList[i + size] = list.get(i);
        }
        collection = newList;
        size += list.size();
    }

    @Override
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index out of bound");
        }
        return collection[index];
    }

    @Override
    public void set(T value, int index) throws ArrayIndexOutOfBoundsException {
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
        T removedElement = null;
        T[] newList = (T[]) new Object[(collection.length)];
        for (int i = 0; i < size; i++) {
            if (i < index) {
                newList[i] = collection[i];
            }
            if (i == index) {
                removedElement = collection[i];
                continue;
            }
            if (i > index) {
                newList[i - 1] = collection[i];
            }
        }
        collection = newList;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T t) throws NoSuchElementException {
        int index = findIndex(t);
        if (index == -1) {
            throw new NoSuchElementException("No Such Element");
        }
        return remove(index);
    }

    private int findIndex(T t) {
        for (int i = 0; i < size; i++) {
            if (collection[i] == t) {
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
        for (int i = 0; i < collection.length; i++) {
            listExpanded[i] = collection[i];
        }
        collection = listExpanded;
    }
}
