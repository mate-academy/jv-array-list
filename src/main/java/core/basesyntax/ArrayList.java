package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] array = new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        if (size >= array.length) {
            expandArray();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        if (array.length < size + 1) {
            expandArray();
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
        checkIndex(index);
        //noinspection unchecked
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
        if (size == array.length) {
            expandArray();
        }
        return arrayReduced(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (element == array[i] || array[i] != null && array[i].equals(element)) {
                return arrayReduced(i);
            }
        }
        throw new NoSuchElementException(
                "There is no such element exists, please try another input.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void expandArray() {
        Object[] newArray = new Object[array.length + (array.length >> 1)];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Wrong index, must be within 0 and " + size);
        }
    }

    private T arrayReduced(int index) {
        @SuppressWarnings("unchecked")
        T element = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
        return element;
    }
}
