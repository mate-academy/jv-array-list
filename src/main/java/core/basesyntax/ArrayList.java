package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private T[] items;
    private int size;

    public ArrayList() {
        items = (T[]) new Object[CAPACITY];
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
        validIndex(value, index);
        if (size == items.length) {
            grow();
        }
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (items.length < size + list.size()) {
            grow();
        }
        System.arraycopy(list.toArray(), 0, items, size, list.size());
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
        System.arraycopy(items, index + 1, items, index, --size - index);
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (items[i] == element || items[i] != null && items[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in list " + element);
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
        T[] newItems = (T[]) new Object[items.length + items.length / 2];
        System.arraycopy(items, 0, newItems, 0, items.length);
        items = newItems;
    }

    private void validIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is not exist");
        }
    }

    private void validIndex(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is not exist");
        }
    }
}
