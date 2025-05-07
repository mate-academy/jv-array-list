package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_SIZE = 10;
    private static final double GROW_COEFFICIENT = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[MAX_SIZE];
    }

    @Override
    public void add(T value) {
        sizeCheck();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index do not exist");
        }
        sizeCheck();
        fastAdd(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexExistCheck(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexExistCheck(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexExistCheck(index);
        final T result = array[index];
        fastRemove(index);
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                final T result;
                result = array[i];
                fastRemove(i);
                return result;
            }
        }
        throw new NoSuchElementException("Element do not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[(int) (array.length * GROW_COEFFICIENT)];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private void fastRemove(int index) {
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        size--;
        array[size] = null;
    }

    private void indexExistCheck(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index do not exist");
        }
    }

    private void fastAdd(T value, int index) {
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    private void sizeCheck() {
        if (array.length == size) {
            grow();
        }
    }
}
