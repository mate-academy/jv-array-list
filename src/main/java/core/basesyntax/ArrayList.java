package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] array;
    private int size = 0;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    private void grow() {
        T[] arrayCopy = (T[]) new Object[array.length * 3 / 2];
        System.arraycopy(array, 0, arrayCopy, 0, size);
        array = arrayCopy;
    }

    private void testIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
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
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Element does not exist");
        }
        if (size == array.length) {
            grow();
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
        testIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        testIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        testIndex(index);
        T value = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || element != null && element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element does not exist");
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
