package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double RATIO = 1.5;
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
            int newCapacity = getNewCapacity();
            Object[] newInternalArray = new Object[newCapacity];
            System.arraycopy(internalArray, 0, newInternalArray, 0, internalArray.length);
            internalArray = newInternalArray;
        }
        internalArray[size - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index not in range");
        }
        size += 1;
        int newCapacity = size > internalArray.length
                ? getNewCapacity()
                : internalArray.length;
        Object[] newInternalArray = new Object[newCapacity];
        System.arraycopy(internalArray, 0, newInternalArray, 0, index);
        newInternalArray[index] = value;
        System.arraycopy(internalArray, index, newInternalArray, index + 1, size - (index + 1));
        internalArray = newInternalArray;
    }

    @Override
    public void addAll(List<T> list) {
        int oldSize = size;
        size += list.size();
        if (size > internalArray.length) {
            int newCapacity = getNewCapacity();
            Object[] newInternalArray = new Object[newCapacity];
            System.arraycopy(internalArray, 0, newInternalArray, 0, internalArray.length);
            internalArray = newInternalArray;
        }
        T[] listToArray = listToArray(list);
        System.arraycopy(listToArray, 0, internalArray, oldSize, list.size());
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index not in range");
        }
        return (T) internalArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index not in range");
        }
        internalArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index not in range");
        }
        Object[] newInternalArray = new Object[internalArray.length];
        System.arraycopy(internalArray, 0, newInternalArray, 0, index);
        int nexIndex = index + 1;
        int copeLength = internalArray.length - nexIndex;
        System.arraycopy(internalArray, nexIndex, newInternalArray, index, copeLength);
        T removedElement = (T) internalArray[index];
        internalArray = newInternalArray;
        size -= 1;
        return removedElement;
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
            if ((internalArray[i] != null && internalArray[i].equals(element))
                    || (internalArray[i] == null && element == null)) {
                return i;
            }
        }
        return -1;
    }

    private T[] listToArray(List<T> list) {
        T[] array = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private int getNewCapacity() {
        return (int) (size * RATIO);
    }
}
