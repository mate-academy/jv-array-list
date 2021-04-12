package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size == elementData.length) {
            grow();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " out of bound, Size:" + size);
        }
        System.arraycopy(elementData, index,
                    elementData, index + 1, size - index);
        elementData[index] = value;
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
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T remove = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index,size - index);
        size--;
        return remove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] != null && elementData[i].equals(element)
                    || elementData[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void grow() {
        T[] newArray = (T[]) new Object[size + size / 2];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " out of bound, Size:" + size);
        }
    }
}
