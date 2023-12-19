package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_LENGTH = 10;
    @SuppressWarnings("unchecked")
    private T[] array = (T[]) new Object[INITIAL_LENGTH];
    private int size;

    @Override
    public void add(T value) {
        growIfRequired();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            growIfRequired();
            System.arraycopy(array, index, array, index + 1, size - index);

            size++;
            array[index] = value;
        }
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
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T element = array[index];

        System.arraycopy(array, index + 1, array, index, size - index - 1);

        size--;
        array[size] = null;

        return element;
    }

    @Override
    public T remove(T element) {
        return remove(getElementIndex(element));
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
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for size " + size + ".");
        }
    }

    private int getElementIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                return i;
            }
        }

        throw new NoSuchElementException("Element not found.");
    }

    private void growIfRequired() {
        if (size == array.length) {
            T[] oldArray = array;
            int len = array.length + (array.length >> 1);
            array = (T[]) new Object[len];

            System.arraycopy(oldArray, 0, array, 0, size);
        }
    }
}
