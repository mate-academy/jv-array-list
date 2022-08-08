package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double INCREASING_RATE = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] internalArray;
    private int size = 0;

    public ArrayList() {
        internalArray = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        size += 1;
        if (size > internalArray.length) {
            increaseCapacity();
        }
        internalArray[size - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndex(index);
        }
        size += 1;
        if (size > internalArray.length) {
            increaseCapacity();
        }
        System.arraycopy(internalArray, index, internalArray, index + 1, size - 1 - index);
        internalArray[index] = value;
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
        return (T) internalArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        internalArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T value = (T) internalArray[index];
        System.arraycopy(internalArray, index + 1, internalArray, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int elementIndex = getIndexOfElement(element);
        if (elementIndex < 0) {
            throw new NoSuchElementException("No such element in ArrayList");
        }
        return remove(elementIndex);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndexOfElement(T element) {
        for (int i = 0; i < size; i++) {
            if (internalArray[i] == element || (internalArray[i] != null
                    && internalArray[i].equals(element))) {
                return i;
            }
        }
        return -1;
    }

    private int getNewCapacity() {
        return (int) (size * INCREASING_RATE);
    }

    private void increaseCapacity() {
        int newCapacity = getNewCapacity();
        Object[] newInternalArray = new Object[newCapacity];
        System.arraycopy(internalArray, 0, newInternalArray, 0, internalArray.length);
        internalArray = newInternalArray;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index not in range");
        }
    }
}
