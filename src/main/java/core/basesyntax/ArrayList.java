package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            resize(size);
        }
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " out of ArrayListSize");
        }

        if (size == arrayList.length) {
            resize(arrayList.length * 2);
        }

        for (int i = size; i > index; i--) {
            arrayList[i] = arrayList[i - 1];
        }

        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int arrayPlusListSize = size + list.size();
        if (arrayPlusListSize > arrayList.length) {
            resize(arrayPlusListSize);
        }

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " out of ArrayListSize");
        }
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " out of ArrayListSize");
        }
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        int numMoved = size - index - 1;
        if (index > size || index < 0 || numMoved < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " out of ArrayListSize");
        }
        T removedElement = (T) arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, numMoved);
        size--;
        arrayList[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (equals(arrayList[i], element)) {
                index = i;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException();
        }
        return remove(index);
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

    public void resize(int size) {
        Object[] newArray = new Object[size + (size / 2)];

        System.arraycopy(arrayList, 0, newArray, 0, arrayList.length);

        arrayList = newArray;
    }

    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
