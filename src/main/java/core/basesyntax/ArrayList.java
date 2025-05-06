package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE_MULTIPLIER = 0.5;
    private T[] items;
    private int size;

    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        items[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        growIfArrayFull();
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); ++i) {
            add(list.get(i));
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
        T deletedValue = items[index];
        int numReplaceLeft = size - index - 1;
        System.arraycopy(items, index + 1, items, index, numReplaceLeft);
        items[--size] = null;
        return deletedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; ++i) {
            if (element == items[i]
                    || element != null && element.equals(items[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(
                "There is not element {" + element + "}in array");
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
            throw new ArrayListIndexOutOfBoundsException(
                    "There is no element with index " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size);
        }
    }

    private void growIfArrayFull() {
        if (size >= items.length) {
            int newLength = items.length + (int) (size * RESIZE_MULTIPLIER);
            T[] newItems = (T[])new Object[newLength];
            System.arraycopy(items, 0, newItems, 0, items.length);
            items = newItems;
        }
    }
}
