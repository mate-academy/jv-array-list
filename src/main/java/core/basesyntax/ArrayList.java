package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_CAPACITY = 10;
    private Object[] array = new Object[MAX_CAPACITY];
    private int size;

    public void grow() {
        Object[] newArray = new Object[size + size / 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    @Override
    public void add(T value) {
        array[size] = value;
        size++;
        if (size == array.length) {
            grow();
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
            "Array list index out of bounds. " + index);
        }
        if (size == array.length) {
            grow();
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if ((size + list.size()) > array.length) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            if (array.length == size) {
                grow();
            }
            array[size] = list.get(i);
            size++;

        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
            "Array list index out of bounds. " + index);
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
            "Array list index out of bounds. " + index);
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
            "Array list index out of bounds. " + index);
        }
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
