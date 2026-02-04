package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity();
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index out of bounds. Index: " + index + ", Size: " + size);
        }
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
        indexOutBounds(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        indexOutBounds(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexOutBounds(index);
        T value = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(element)
                    || array[i] == null && element == null) {
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            Object[] newArray = new Object[array.length + array.length / 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void indexOutBounds(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index out of bounds. Index: " + index + ", Size: " + size);
        }
    }

}
