package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        array[size] = value;
        size++;
        growIfRequared();
    }

    @Override
    public void add(T value, int index) {
        checkIndexData(index);
        growIfRequared();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        growIfRequared();
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
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index,size - index - 1);
        T removedValue = (T) array[index];
        array = newArray;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        T removedValue;
        for (int i = 0; i <= size - 1; i++) {
            if ((array[i] != null && array[i].equals(element))
                    || (element == null && array[i] == null)) {
                removedValue = (T) array[i];
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                array[size - 1] = null;
                size--;
                return removedValue;
            }
        }
        throw new NoSuchElementException(
        "Element not found. " + element);
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
        T[] newArray = (T[]) new Object[size + size / 2];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void growIfRequared() {
        if (size == array.length) {
            grow();
        }
    }

    private void checkIndexData(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Array list index out of bounds. " + index);
        }
    }

    private void checkIndexDataIfSizeEqualOrMore(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Array list index out of bounds. " + index);
        }
    }
}
