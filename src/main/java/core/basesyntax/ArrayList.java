package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;
    private static final int FIRST_INDEX = 0;
    private static final int INCREMENT = 1;
    private static final double COEFFICIENT_INCREASE_ARRAY = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[ARRAY_SIZE];
    }

    private void acceptIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index out of bounds, please check input index");
        }
    }

    private void increaseArray() {
        if (size + INCREMENT > array.length) {
            T[] newArray = (T[]) new Object[(int) (array.length * COEFFICIENT_INCREASE_ARRAY)];
            System.arraycopy(array, FIRST_INDEX, newArray, FIRST_INDEX, size);
            array = newArray;
        }
    }

    @Override
    public void add(T value) {
        if (size + INCREMENT > array.length) {
            increaseArray();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        acceptIndex(index);
        increaseArray();
        System.arraycopy(array, index, array, index + INCREMENT, size - index);
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
        acceptIndex(index + INCREMENT);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        acceptIndex(index + INCREMENT);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        acceptIndex(index);
        T removed = array[index];
        System.arraycopy(array, index + INCREMENT, array, index, size - index + INCREMENT);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null || element.equals(array[i])) {
                System.arraycopy(array, i + INCREMENT, array, i, size - i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Index out of bounds, please check input index");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
