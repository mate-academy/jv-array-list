package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final float GROW_FACTOR = 1.5f;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] items;
    private int size;

    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == items.length) {
            grow();
        }
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (size == items.length) {
            grow();
        }
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = value;
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
        T removedItems = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        size--;
        return removedItems;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < items.length; i++) {
            if (element != null && element.equals(items[i]) || element == items[i]) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't find the element " + element + " in this list");
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
        T[] tempArray = (T[]) new Object[(int) (items.length * GROW_FACTOR)];
        System.arraycopy(items, 0, tempArray, 0, items.length);
        items = tempArray;
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index"
                    + index + "doesn't exist or is less than 0");
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index"
                    + index + "doesn't exist or is less than 0");
        }
    }
}
