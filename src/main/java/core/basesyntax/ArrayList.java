package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int EMPTY_SIZE = 0;
    private static final double SIZE_MULTIPLIER = 1.5;
    private int size;
    private T[] items;

    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkForGrow();
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndexOfBound(index);
        checkForGrow();
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            checkForGrow();
            items[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndexOfBound(index);
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexOfBound(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexOfBound(index);
        final T removedItem = items[index];
        for (int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        size--;
        items[size] = null;
        return removedItem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < items.length; i++) {
            if (element == items[i] || element != null && element.equals(items[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " are not exist.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY_SIZE;
    }

    private void grow() {
        int newSize = (int) (items.length * SIZE_MULTIPLIER);
        T[] oldItems = items;
        items = (T[]) new Object[newSize];
        System.arraycopy(oldItems, 0, items, 0, size);
    }

    private void checkForGrow() {
        if (size + 1 > items.length) {
            grow();
        }
    }

    private void checkIndexOfBound(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bound.");
        }
    }
}
