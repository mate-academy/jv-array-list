package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ITEMS_NUMBER = 10;
    private T[] items;
    private int size;

    public ArrayList() {
        items = (T[]) new Object[DEFAULT_ITEMS_NUMBER];
    }

    @Override
    public void add(T value) {
        if (size() == items.length) {
            expandArray();
        }
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == items.length) {
            expandArray();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of Bound");
        }
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0;i < list.size();i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexRange(index);
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        indexRange(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        indexRange(index);
        final T elementToReturn = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        items[--size] = null;
        return elementToReturn;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (items[i] == element
                    || (items[i] != null && items[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void expandArray() {
        int nextLength = items.length + (items.length >> 1);
        T[] resizedElements = (T[]) new Object[nextLength];
        System.arraycopy(items, 0, resizedElements, 0, items.length);
        items = resizedElements;
    }

    private void indexRange(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }
}
