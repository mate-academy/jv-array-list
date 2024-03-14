package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROW_CONSTANT = 2;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize(size);
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " out of ArrayListSize");
        }
        resize(arrayList.length * GROW_CONSTANT);
        System.arraycopy(arrayList, index, arrayList, index + 1,  size - index);
        arrayList[index] = value;
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
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        int numMoved = size - index - 1;
        if (index > size || index < 0 || numMoved < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " out of ArrayListSize");
        }
        final T removedElement = (T) arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, numMoved);
        size--;
        arrayList[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (equals(arrayList[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " out of ArrayListSize");
        }
    }

    private void resize(int size) {
        if (size == arrayList.length) {
            T[] newArray = (T[]) new Object[size + (size / GROW_CONSTANT)];
            System.arraycopy(arrayList, 0, newArray, 0, arrayList.length);
            arrayList = newArray;
        }
    }

    private static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
