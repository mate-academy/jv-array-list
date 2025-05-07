package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        growIfRequired();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexData(index);
        growIfRequired();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
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
        checkIndexDataIfSizeEqualOrMore(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexDataIfSizeEqualOrMore(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexDataIfSizeEqualOrMore(index);
        T[] newArray = (T[]) new Object[array.length];
        T removedValue = (T) array[index];
        System.arraycopy(array, index + 1, array, index,size() - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        T removedValue;
        for (int i = 0; i <= size - 1; i++) {
            if (array[i] != null && array[i].equals(element)
                    || element == array[i]) {
                removedValue = (T) array[i];
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                array[--size] = null;
                return removedValue;
            }
        }
        throw new NoSuchElementException("Element not found. " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    private void grow() {
        int growFactor = size + size / 2;
        T[] newArray = (T[]) new Object[growFactor];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void growIfRequired() {
        if (size == array.length) {
            grow();
        }
    }

    private void checkIndexData(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Array list index out of bounds. " + index + ",size: " + size);
        }
    }

    private void checkIndexDataIfSizeEqualOrMore(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Array list index out of bounds. " + index + ",size: " + size);
        }
    }
}
