package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String NO_POSITION_MESSAGE = "This position is not exist in list ";
    private static final String NO_ELEMENT_MESSAGE = "There is no such element in list ";
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
        validIndex(value, index);
        if (size == capacity) {
            grow();
        }
        System.arraycopy(items, index, items, index + ONE, size - index);
        items[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (capacity < size + list.size()) {
            grow();
        }
        System.arraycopy(list.toArray(), ZERO, items, size, list.size());
        size += list.size();
    }

    @Override
    public T get(int index) {
        validIndex(index);
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        validIndex(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        validIndex(index);
        T removedObject = items[index];
        System.arraycopy(items, index + ONE, items, index, --size - index);
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = ZERO; i < size; i++) {
            if (items[i] == element || items[i] != null && items[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_ELEMENT_MESSAGE + element);
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

    public void validIndex(int index) {
        if (index < ZERO || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(NO_POSITION_MESSAGE + index);
        }
    }

    public void validIndex(T value, int index) {
        if (index < ZERO || index > size) {
            throw new ArrayListIndexOutOfBoundsException(NO_POSITION_MESSAGE + index);
        }
    }
}
