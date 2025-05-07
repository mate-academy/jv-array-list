package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] entries;
    private int size;

    public ArrayList() {
        entries = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (size == entries.length) {
            grow();
        }
        if (index != size) {
            checkIndex(index);
            System.arraycopy(entries, index, entries, index + 1, size - index);
        }
        entries[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new RuntimeException("List can't be null!");
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return entries[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        entries[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T foundObject = entries[index];
        System.arraycopy(entries, index + 1, entries, index,--size - index);
        return foundObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (customEquals(entries[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't find element " + element);
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index
                    + " is invalid index for size " + size);
        }
    }

    private void grow() {
        T[] tempEntries = (T[]) new Object[entries.length + (entries.length >> 1)];
        System.arraycopy(entries, 0, tempEntries, 0, size);
        entries = tempEntries;
    }

    private boolean customEquals(Object a, Object b) {
        return a == null ? a == b : a.equals(b);
    }
}
