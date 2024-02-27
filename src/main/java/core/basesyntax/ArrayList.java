package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double CAPACITY_INCREASE_MODIFICATOR = 1.5;
    private Object[] items;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new ArrayListIndexOutOfBoundsException("Initial capacity must be > 0");
        }
        items = new Object[initialCapacity];
    }

    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        items[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size);
        growIfArrayFull();
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
        T removedValue = (T) items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        items[--size] = null;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? items[i] == null : element.equals(items[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size == items.length) {
            int newCapacity = (int) (items.length * CAPACITY_INCREASE_MODIFICATOR);
            Object[] newObjectArr = new Object[newCapacity];
            System.arraycopy(items, 0, newObjectArr, 0, size);
            items = newObjectArr;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + index);
        }
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + index);
        }
    }
}
