package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            arrayListGrow();
        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index low than 0 or bigger than array size");
        }
        if (size == arrayList.length) {
            arrayListGrow();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index low than 0 or bigger than array size");
        }
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index low than 0 or bigger than array size");
        }
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index low than 0 or bigger than array size");
        }
        T removedElement = (T) arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == element || arrayList[i] != null && arrayList[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is not such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] arrayListGrow() {
        T[] newArrayList = (T[]) new Object[(int)((arrayList.length + (arrayList.length * 2)) / 2)];
        System.arraycopy(arrayList, 0, newArrayList, 0, arrayList.length);
        arrayList = (T[]) newArrayList;
        return newArrayList;
    }
}

