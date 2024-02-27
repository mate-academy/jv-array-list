package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    transient Object[] defaultList;
    private int size;
    private static final double CAPACITY_INCREASE_MODIFICATOR = 1.5;

    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new ArrayListIndexOutOfBoundsException("Initial capacity must be > 0");
        }
        defaultList = new Object[initialCapacity];
    }

    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    @Override
    public void add(T value) {
        increaseCapacity();
        defaultList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index not found");
        }
        increaseCapacity();
        System.arraycopy(defaultList, index, defaultList, index +1, size - index);
        defaultList[index] = value;
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
        return (T) defaultList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        defaultList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = (T) defaultList[index];
        System.arraycopy(defaultList, index + 1, defaultList, index, size - index - 1);
        defaultList[--size] = null;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? defaultList[i] == null : element.equals(defaultList[i])) {
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

    private void increaseCapacity() {
        if (size == defaultList.length) {
            int newCapacity = (int) (defaultList.length * CAPACITY_INCREASE_MODIFICATOR);
            Object[] newObjectArr = new Object[newCapacity];
            System.arraycopy(defaultList, 0, newObjectArr, 0, size);
            defaultList = newObjectArr;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index not found");
        }
    }
}
