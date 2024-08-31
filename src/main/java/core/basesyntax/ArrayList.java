package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            T[] newArray = (T[]) new Object[(int) (array.length * 1.5)];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if ((index < 0) || (index > size)) {
            throw new ArrayListIndexOutOfBoundsException("The index extends beyond the array.");
        }
        if (size == array.length) {
            T[] newArray = (T[]) new Object[(int) (array.length * 1.5)];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
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
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if ((index < 0) || (index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("The index extends beyond the array.");
        }
        final T removedElement = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (array[i] != null && array[i].equals(element)) {
                    return remove(i);
                }
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
