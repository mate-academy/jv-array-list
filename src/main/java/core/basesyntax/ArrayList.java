package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;
    private static final int FIRST_INDEX = 0;
    private static final int INCREMENT = 1;
    private static final double COEFFICIENT_INCREASE_ARRAY = 1.5;
    private Object[] array;
    private int size;

    public ArrayList() {
        this.array = new Object[ARRAY_SIZE];
    }

    private void acceptIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index out of bounds, please check input index");
        }
    }

    private void increaseArray() {
        if (size + INCREMENT > array.length) {
            Object[] newArray = new Object[(int) (array.length * COEFFICIENT_INCREASE_ARRAY)];
            System.arraycopy(array, FIRST_INDEX, newArray, FIRST_INDEX, size);
            this.array = newArray;
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
        Object[] newArray = new Object[array.length];
        System.arraycopy(array, FIRST_INDEX, newArray, FIRST_INDEX, index);
        newArray[index] = value;
        System.arraycopy(array, index, newArray, index + INCREMENT, size - index);
        this.array = newArray;
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
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        acceptIndex(index + INCREMENT);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        acceptIndex(index);
        Object[] newArray = new Object[array.length];
        T removed = (T) array[index];
        System.arraycopy(array, FIRST_INDEX, newArray, FIRST_INDEX, index + INCREMENT);
        System.arraycopy(array, index + INCREMENT, newArray, index, size - index + INCREMENT);
        size--;
        this.array = newArray;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null || element.equals(array[i])) {
                Object[] newArray = new Object[array.length];
                System.arraycopy(array, FIRST_INDEX, newArray, FIRST_INDEX, i);
                System.arraycopy(array, i + INCREMENT, newArray, i, size - i);
                size--;
                this.array = newArray;
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
