package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROWTH_INDEX = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            resizeArray();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index [" + index + "] is invalid");
        }
        if (size < array.length) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        } else {
            resizeArray();
            add(value, index);
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
        T removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " is'nt present in ArrayList");
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
            throw new ArrayListIndexOutOfBoundsException("index [" + index + "] is invalid");
        }
    }

    private void resizeArray() {
        T[] resizedArray = (T[]) new Object[(int) (array.length * GROWTH_INDEX)];
        System.arraycopy(array, 0, resizedArray, 0, array.length);
        array = resizedArray;
    }
}
