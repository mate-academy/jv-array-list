package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_LENGTH = 10;
    @SuppressWarnings("unchecked")
    private T[] array = (T[]) new Object[INITIAL_LENGTH];
    private int size;

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }

        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);

            if (size == array.length) {
                grow();
            }

            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }

            size++;
            array[index] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        int requiredSize = size + list.size();

        if (array.length < requiredSize) {
            int length = array.length;

            while (length < requiredSize) {
                length += length >> 1;
            }

            array = Arrays.copyOf(array, length);
        }

        for (int i = 0; i < list.size(); i++) {
            array[size++] = list.get(i);
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

        for (int i = index + 1; i < size; i++) {
            array[i - 1] = array[i];
        }

        size--;
        array[size] = null;

        return element;
    }

    @Override
    public T remove(T element) {
        return remove(findElement(element));
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

    private int findElement(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, array[i])) {
                return i;
            }
        }

        throw new NoSuchElementException("Element not found.");
    }

    private void grow() {
        int len = array.length + (array.length >> 1);
        array = Arrays.copyOf(array, len);
    }
}
