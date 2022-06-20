package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] items;
    private int size;

    public ArrayList() {
        items = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
        resize();
        if (size > index) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }
        items[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0;i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) items[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object deletedItem = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        size--;
        return (T) deletedItem;
    }

    @Override
    public T remove(T element) {
        int index = getIndexByValue(element);
        Object deletedItem = items[index];
        System.arraycopy(items,index + 1, items, index,size - index);
        items[size--] = null;
        return (T) deletedItem;
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
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " is invalid, size: "
                    + size);
        }
    }

    private int getIndexByValue(T value) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == value || items[i] != null && items[i].equals(value)) {
                return i;
            }
        }
        throw new NoSuchElementException("Not found element: " + value);
    }

    private void resize() {
        if (size == items.length) {
            Object[] newItems = new Object[(int) (items.length + items.length / 2)];
            System.arraycopy(items, 0, newItems, 0, items.length);
            items = newItems;
        }
    }
}
