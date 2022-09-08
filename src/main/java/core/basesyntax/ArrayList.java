package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_RATE = 1.5;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        handleResize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        validateIndex(index);
        handleResize();
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < size; i++) {
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
        T elementToRemove = array[index];
        size--;
        System.arraycopy(array, index + 1, array, index, size - index);
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " was not found");
    }

    @Override
    public int size() {
        return size;
    }

    private void increaseSize() {
        T[] tempArray = (T[]) new Object[(int) (size * INCREASE_RATE)];
        System.arraycopy(array, 0, tempArray, 0, size);
        array = tempArray;

    }

    private void handleResize() {
        if (size == array.length) {
            increaseSize();
        }
    }

    private void validateIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index " + index
                    + "is out of bounds for size " + size);
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
