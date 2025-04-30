package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        array = (T[]) new Object[capacity];
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
        checkIndexNotInBounds(index, size);

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
        checkIndexNotInBounds(index, size - 1);

        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexNotInBounds(index, size - 1);

        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexNotInBounds(index, size - 1);

        final T removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i]
                    || element != null && element.equals(array[i])) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("This element was not found!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder strOutput = new StringBuilder();

        for (T element : array) {
            if (element != null) {
                strOutput.append(element).append(", ");
            }
        }

        return strOutput.toString();
    }

    private void grow() {
        T[] increasedArray = (T[]) new Object[size + (size >> 1) + 1];
        System.arraycopy(array, 0, increasedArray, 0, size);

        array = increasedArray;
    }

    private void checkIndexNotInBounds(int index, int range) {
        if (index < 0 || index > range) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index is out of bounds. Size of the ArrayList is "
                            + size + ". Your index is " + index
            );
        }
    }
}
