package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

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

    private void arrayListIndexOutOfBoundsExceptionMethodIfSizeMore(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Array list index out of bounds. " + index);
        }
    }

    private void arrayListIndexOutOfBoundsExceptionMethodIfSizeMoreOrEqual(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Array list index out of bounds. " + index);
        }
    }

    @Override
    public void add(T value) {
        array[size] = value;
        size++;
        growIfRequared();
    }

    @Override
    public void add(T value, int index) {
        arrayListIndexOutOfBoundsExceptionMethodIfSizeMore(index);
        growIfRequared();
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
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
        arrayListIndexOutOfBoundsExceptionMethodIfSizeMoreOrEqual(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        arrayListIndexOutOfBoundsExceptionMethodIfSizeMoreOrEqual(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        arrayListIndexOutOfBoundsExceptionMethodIfSizeMoreOrEqual(index);
        T removedValue = (T) array[index];
        for (int i = index; i <= size - 1; i++) {
            array[i] = array[i + 1];
        }
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
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
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
}
