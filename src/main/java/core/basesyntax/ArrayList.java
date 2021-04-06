package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double EXPAND_MULTIPLIER = 1.5;
    private T[] myArrayList;

    private int size = 0;

    public ArrayList() {
        this.myArrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == myArrayList.length) {
            expand();
        }
        myArrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        if (size == myArrayList.length) {
            expand();
        }
        System.arraycopy(myArrayList, index, myArrayList, index + 1, size - index);
        myArrayList[index] = value;
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
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        return myArrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        myArrayList[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        T removedElement = myArrayList[index];
        System.arraycopy(myArrayList, index + 1, myArrayList, index, size - index - 1);
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
            if ((myArrayList[i] == t) || myArrayList[i] != null && myArrayList[i].equals(t)) {
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
        T[] listExpanded = (T[]) new Object[(int) (myArrayList.length * EXPAND_MULTIPLIER)];
        System.arraycopy(myArrayList, 0, listExpanded, 0, myArrayList.length);
        myArrayList = listExpanded;
    }
}
