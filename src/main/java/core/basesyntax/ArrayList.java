package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MAGNIFICATION_FACTOR = 1.5;
    private int size;
    private T[] items;

    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == items.length) {
            resize();
        }
        items[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (size == items.length) {
            resize();
        }
        if (index <= size) {
            System.arraycopy(items, index, items, index + 1, size++ - index);
            items[index] = value;
        }
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
        T deletedObject = items[index];
        if (index + 1 == items.length) {
            size--;
            return deletedObject;
        }
        System.arraycopy(items, index + 1, items, index, size-- - index);
        return deletedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Element " + element + " non-existent");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size >= 1) {
            return false;
        }
        return true;
    }

    private T[] resize() {
        Object[] newArray = new Object[items.length + (items.length >> 1)];
        System.arraycopy(items, 0, newArray, 0, size);
        items = (T[]) newArray;
        return items;
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + " non-existent");
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + " non-existent");
        }
    }
}
