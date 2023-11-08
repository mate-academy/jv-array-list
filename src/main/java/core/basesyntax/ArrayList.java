package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T [] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object [DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            resize(array.length);
        }
        checkIndex(size);
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
        if (size == array.length) {
            resize(array.length);
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
        checkIndex(index + 1);
        return array[index];
    }

    public void set(T value, int index) {
        checkIndex(index + 1);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index + 1);
        T indexElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        size--;
        return indexElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (equals(element, array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't find element " + element);
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
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound: " + index);
        }
    }

    private boolean equals(T firstElement, T secondElement) {
        return firstElement == secondElement
                || firstElement != null && firstElement.equals(secondElement);
    }

    private void resize(int actualLength) {
        Object[]newArray = new Object[(int) (actualLength * GROWTH_FACTOR)];
        System.arraycopy(array, 0, newArray, 0, size);
        array = (T[])newArray;
    }
}
