package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double MULTIPLIER = 1.5;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkSize();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size + 1);
        checkSize();
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
        checkIndex(index, size);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element
                    || (array[i] != null) && array[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkSize() {
        if (array.length == size) {
            T[] newArray = (T[]) new Object[(int) (array.length * MULTIPLIER)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }
    }
}
