package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE_MULTIPLIER = 0.5;
    private T[] items;
    private int size = 0;

    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        items[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, true);
        grow();
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
        checkIndex(index, false);
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, false);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);
        T deletedValue = items[index];
        int numReplaceLeft = size - index - 1;
        System.arraycopy(items, index + 1, items, index, numReplaceLeft);
        items[--size] = null;
        return deletedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; ++i) {
            if ((element == null && items[i] == null)
                    || (element != null && element.equals(items[i]))) {
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

    private void checkIndex(int index, boolean isAddOperation) {
        if (isAddOperation) {
            if (index < 0 || index > size) {
                throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
        } else {
            if (index < 0 || index >= size) {
                throw new ArrayListIndexOutOfBoundsException(
                        "There is no element with index " + index);
            }
        }
    }

    private void grow() {
        if (size >= items.length) {
            int newLength = items.length + (int) (size * RESIZE_MULTIPLIER);
            T[] newItems = (T[])new Object[newLength];
            System.arraycopy(items, 0, newItems, 0, items.length);
            items = newItems;
        }
    }
}
