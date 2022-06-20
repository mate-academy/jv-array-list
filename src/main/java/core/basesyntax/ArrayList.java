package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBERS = 10;
    private Object[] items;
    private int size;

    public ArrayList() {
        items = new Object[MAX_ITEMS_NUMBERS];
    }

    @Override
    public void add(T value) {
        coppyArray();
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index " + index);
        }
        coppyArray();
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
        Object deletedIndex = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        size--;
        return (T) deletedIndex;
    }

    @Override
    public T remove(T element) {
        int index = getIndexByValue(element);
        Object deleted = items[index];
        System.arraycopy(items,index + 1, items, index,size - index);
        items[size--] = null;
        return (T) deleted;
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
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid " + size);
        }
    }

    private int getIndexByValue(T value) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == value || items[i] != null && items[i].equals(value)) {
                return i;
            }
        }
        throw new NoSuchElementException("Not found element " + value);
    }

    private void coppyArray() {
        if (size == items.length) {
            Object[] newItems = new Object[(int) (items.length + items.length / 2)];
            System.arraycopy(items, 0, newItems, 0, items.length);
            items = newItems;
        }
    }
}
