package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_MAX_SIZE = 10;
    private int size = 0;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_MAX_SIZE];
    }

    public void grow() {
        T[] newArray = (T[]) new Object[(int) (array.length * 1.5)];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size == array.length) {
            grow();
        }

        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }

        T deletedValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return deletedValue;
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < size; index++) {
            if (element == null ? array[index] == null : array[index].equals(element)) {
                System.arraycopy(array, index + 1, array, index, size - index - 1);
                array[--size] = null;
                return element;
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
