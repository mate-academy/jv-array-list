package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T [] arrayListData;
    private int size = 0;

    public ArrayList() {
        arrayListData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T e) {
        if (size == arrayListData.length) {
            ensureCapacity();
        }
        arrayListData[size] = e;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        if (size == arrayListData.length) {
            ensureCapacity();
        }
        System.arraycopy(arrayListData, index, arrayListData, index + 1, size - index);
        arrayListData[index] = value;
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
        return arrayListData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayListData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = arrayListData[index];
        System.arraycopy(arrayListData, index + 1, arrayListData, index, size - index - 1);
        arrayListData[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((arrayListData[i] != null && arrayListData[i].equals(element))
                        || arrayListData[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
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
            throw new ArrayListIndexOutOfBoundsException("Checked index is wrong");
        }
    }

    private void ensureCapacity() {
        T [] newArray = (T[]) new Object[arrayListData.length + (arrayListData.length >> 1)];
        System.arraycopy(arrayListData, 0, newArray, 0, arrayListData.length);
        arrayListData = newArray;
    }
}
