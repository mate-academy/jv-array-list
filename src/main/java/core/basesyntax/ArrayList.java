package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_CAPACITY = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " is not valid for size " + size + ".");
        }
        if (size == array.length) {
            grow();
        }

        if (index < size) {
            System.arraycopy(array, index, array, index + 1, size() - index);
            array[index] = value;
            size++;
        }
        if (index == size) {
            add(value);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T remove(int index) {
        Object removeElement;
        checkIndex(index);
        removeElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return (T) removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if ((array[i] == element)
                    || element != null && element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found " + element);
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " is not valid for size " + size + ".");
        }
    }

    private void grow() {
        T[] arrayCopy = (T[]) new Object[(int) (array.length * GROWTH_CAPACITY)];
        System.arraycopy(array, 0, arrayCopy, 0, array.length);
        array = arrayCopy;
    }
}
