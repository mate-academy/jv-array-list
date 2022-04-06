package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBER = 10;
    private int size = 0;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[MAX_ITEMS_NUMBER];
    }

    @Override
    public void add(T value) {
        grow();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index <= size && index >= 0) {
            grow();
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            grow();
            array[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return array[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T removedElement = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            return removedElement;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if ((array[i] != null && array[i].equals(element)) || array[i] == element) {
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Invalid element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
        if (size >= MAX_ITEMS_NUMBER) {
            T[] newArray = (T[]) new Object[(int)(size * 1.5)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }
}
