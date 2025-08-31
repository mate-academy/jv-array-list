package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_NUMERATOR = 3;
    private static final int GROWTH_DENOMINATOR = 2;
    private int size;
    private T[] items;

    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == items.length) {
            items = growArray(items, GROWTH_NUMERATOR * items.length / GROWTH_DENOMINATOR);
        }
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " is out of bounds");
        }
        if (size == items.length) {
            items = growArray(items, GROWTH_NUMERATOR * items.length / GROWTH_DENOMINATOR);
        }
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("addAll(list) list must not be null");
        }
        if (size + list.size() > items.length) {
            int newLenght = items.length;
            while (newLenght < size + list.size()) {
                newLenght = GROWTH_NUMERATOR * newLenght / GROWTH_DENOMINATOR;
            }
            items = growArray(items, newLenght);
        }
        for (int i = 0; i < list.size(); i++) {
            items[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T result = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        items[--size] = null;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null ? element.equals(items[i]) : items[i] == null) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] growArray(T[] array, int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        System.arraycopy(array, 0, newItems, 0, array.length);
        return newItems;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " out of bounds for size " + size);
        }
    }
}
