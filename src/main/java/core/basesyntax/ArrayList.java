package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_ARRAY_SIZE = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[INITIAL_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The array index is out of bounds");
        }
        checkCapacity();
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
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T item = array[index];
        if (size != index + 1) {
            System.arraycopy(array, index + 1, array, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? array[i] == null : element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(element + " does not exist in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (size + (size >> 1) > Integer.MAX_VALUE) {
            throw new ArrayListIndexOutOfBoundsException("The maximum size of the array reached - "
                    + Integer.MAX_VALUE);
        }
        T[] newArray = (T[]) new Object[size + (size >> 1)];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The array index " + index
                    + " is out of bounds - 0.." + (size - 1));
        }
    }

    private void checkCapacity() {
        if (size == array.length) {
            grow();
        }
    }
}
