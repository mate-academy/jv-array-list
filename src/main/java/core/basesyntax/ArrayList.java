package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    private T[] array = (T[]) new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkPositionIndex(index);
        ensureCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            ensureCapacity();
            array[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);

        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkElementIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        T result = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementsEqual(array[i], element)) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("element: " + element + " not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean elementsEqual(T first, T second) {
        if (first == second) {
            return true;
        }

        if (first == null || second == null) {
            return false;
        }

        return first.equals(second);
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Element index " + index + " is out of bounds for size " + size
            );
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Position index " + index + " is out of bounds for size " + size
            );
        }
    }

    private void ensureCapacity() {
        if (size == array.length) {
            grow();
        }
    }

    private void grow() {
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[array.length + (array.length >> 1)];

        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}
