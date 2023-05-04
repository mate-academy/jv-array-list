package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    private T[] array = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        while (size >= array.length) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        while (size >= array.length) {
            grow();
        }
        System.arraycopy(array, index,
                array, index + 1, array.length - index - 1);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.toArray().length >= array.length) {
            grow();
        }
        System.arraycopy(list.toArray(), 0, array, size, list.toArray().length);
        size += list.size();
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        if (index == array.length - 1) {
            T value = array[index];
            array[index] = null;
            size--;
            return value;
        }
        T value = array[index];
        System.arraycopy(array,
                index + 1, array, index, array.length - index - 2);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        if (findIndexOfElement(element) != -1) {
            return remove(findIndexOfElement(element));
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public T[] toArray() {
        return Arrays.copyOf(array, array.length);
    }

    private void grow() {
        array = Arrays.copyOf(array, array.length + (array.length >> 1));
    }

    private int findIndexOfElement(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private void indexCheck(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
