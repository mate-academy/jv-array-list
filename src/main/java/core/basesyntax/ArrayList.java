package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBER = 10;
    private static final int NEXT_ITEM = 1;

    private int count;
    private Object[] items;

    public ArrayList() {
        items = new Object[MAX_ITEMS_NUMBER];
        count = 0;
    }

    private void grow() {
        int newCapacity = items.length + (items.length >> 1);
        Object[] newItems = new Object[newCapacity];
        System.arraycopy(items, 0, newItems, 0, items.length);
        items = newItems;
    }

    private void validateIndex(int index) {
        if (index >= count || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index + ", Size: " + count);
        }
    }

    @Override
    public void add(T value) {
        if (items.length == count) {
            grow();
        }
        items[count] = value;
        count++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > count) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + count);
        }
        if (count == items.length) {
            grow();
        }
        System.arraycopy(items, index, items, index + 1, count - index);
        items[index] = value;
        count++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return (T) items[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index + ", Size: " + count);
        }
        T removedElement = (T) items[index];
        int numMoved = count - index - NEXT_ITEM;
        if (numMoved > 0) {
            System.arraycopy(items, index + NEXT_ITEM, items, index, numMoved);
        }
        items[--count] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < count; i++) {
            if (element == items[i] || (element != null && element.equals(items[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }
}
