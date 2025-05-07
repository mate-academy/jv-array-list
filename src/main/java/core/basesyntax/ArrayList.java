package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double RESIZE_FACTOR = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            array = resizeArray(array);
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);

        if (size == array.length) {
            array = resizeArray(array);
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
        final T removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (element == null && array[i] == null
                    || element != null && element.equals(array[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException(element + "not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] resizeArray(T[] array) {
        T[] tempArray = (T[]) new Object[(int) (array.length * RESIZE_FACTOR)];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        array = tempArray;
        return array;
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index > size");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index > size");
        }
    }
}
