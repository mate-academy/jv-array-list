package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int COUNT_OF_ELEMENTS = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[COUNT_OF_ELEMENTS];
        ;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            expandArray();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index: " + index);
        }
        if (size >= array.length) {
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
        T element = get(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(element, array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can`t find element " + element);
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
        T[] expandedArray = (T[]) new Object[array.length * array.length / 2];
        System.arraycopy(array, 0, expandedArray, 0, array.length);
        array = expandedArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index: " + index);
        }
    }
}
