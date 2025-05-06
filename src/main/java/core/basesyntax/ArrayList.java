package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int INITIAL_CAPACITY = 10;
    private static final double RESIZE_FACTOR = 1.5;

    private T[] array;
    private int size = 0;

    public ArrayList() {
        array = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        ensureCapacity();
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
        validateIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        final T targetElement = array[index];
        size--;
        System.arraycopy(array, index + 1, array, index, size - index);
        array[size] = null;
        return targetElement;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    final T targetElement = array[i];
                    size--;
                    System.arraycopy(array, i + 1, array, i, size - i);
                    array[size - 1] = null;
                    return targetElement;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(array[i])) {
                    final T targetElement = array[i];
                    size--;
                    System.arraycopy(array, i + 1, array, i, size - i);
                    array[size] = null;
                    return targetElement;
                }
            }
        }
        throw new NoSuchElementException("Item not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size >= array.length) {
            resize();
        }
    }

    private void resize() {
        int newCapacity = (int) (array.length * RESIZE_FACTOR);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds. Valid index values: from 0 to " + (size - 1));
        }
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index" + index
                    + " out of bounds for add operation. Valid index values: from 0 to" + size);
        }
    }
}
