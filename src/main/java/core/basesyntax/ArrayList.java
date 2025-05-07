package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int INITIAL_CAPACITY = 10;
    private static final double COEFFICIENT = 1.5;

    private T[] array = (T[]) new Object[INITIAL_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        ensureCapacity();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexBounds(index < 0);
        ensureCapacity();
        if (index == size) {
            add(value);
        } else {
            T t = array[index];
            checkIndexBounds(t == null);
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
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
        checkIndexBounds(isCorrectIndex(index));
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBounds(isCorrectIndex(index));
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(isCorrectIndex(index));
        T t = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return t;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (element == null && array[i] == null) {
                size--;
                return null;
            }
            if (array[i] != null && array[i].equals(element)) {
                index = i;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException();
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == array.length - 1;
    }

    private T[] grow() {
        T[] newArray = (T[]) new Object[(int) (array.length * COEFFICIENT)];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private void ensureCapacity() {
        if (isFull()) {
            array = grow();
        }
    }

    private void checkIndexBounds(boolean index) {
        if (index) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index!");
        }
    }

    private boolean isCorrectIndex(int index) {
        return index >= size || index < 0;
    }
}
