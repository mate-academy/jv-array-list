package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int capacity = 10;
    private static final int ONE = 1;
    private static final int ZERO = 0;
    private T[] items;
    private int size;

    public ArrayList() {
        items = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            grow();
        }
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < ZERO || index > size) {
            throw new ArrayListIndexOutOfBoundsException("This position is not exist in list");
        }
        if (size == capacity) {
            grow();
        }
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items, ZERO, newItems, ZERO, index);
        newItems[index] = value;
        System.arraycopy(items, index, newItems, index + ONE, size - index);
        items = newItems;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (capacity < size + list.size()) {
            grow();
        }
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items, ZERO, newItems, ZERO, size);
        System.arraycopy(list.toArray(), ZERO, newItems, size, list.size());
        items = newItems;
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < ZERO || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This position is not exist in list");
        }
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < ZERO || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This position is not exist in list");
        }
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < ZERO || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This position is not exist in list");
        }
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items, ZERO, newItems, ZERO, index);
        T removedObject = items[index];
        System.arraycopy(items, index + ONE, newItems, index, --size - index);
        items = newItems;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = ZERO; i < size; i++) {
            if (items[i] == element || items[i] != null && items[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == ZERO;
    }

    public void grow() {
        int oldCapacity = capacity;
        capacity += capacity >> ONE;
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items, ZERO, newItems, ZERO, oldCapacity);
        items = newItems;
    }
}
