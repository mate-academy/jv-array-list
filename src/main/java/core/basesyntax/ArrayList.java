package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWS = 1.5;
    private int size;
    private T[] arrayList;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            grow();
        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index
                    + ", ArrayList size: " + size);
        }
        if (size == arrayList.length) {
            grow();
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
        checkIndex(index, size);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T removed = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        T removed = null;
        int index = -1;
        if (element == null) {
            for (int i = 0; i < arrayList.length; i++) {
                if (arrayList[i] == null) {
                    index = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < arrayList.length; i++) {
                if (arrayList[i] != null && arrayList[i].equals(element)) {
                    index = i;
                    removed = arrayList[index];
                    System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
                    break;
                }
            }
        }
        if (index == -1) {
            throw new NoSuchElementException();
        }
        size--;
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index
                    + ", ArrayList size: " + size);
        }
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        int growCapacity = size == 0
                ? DEFAULT_CAPACITY : (int) (size * GROWS);
        T[] growArrayList = (T[]) new Object[growCapacity];
        System.arraycopy(arrayList, 0, growArrayList, 0, size);
        arrayList = growArrayList;
    }
}
