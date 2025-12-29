package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROW_BY = 1.5;
    private static final int DEFAULT_SIZE = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private int size = DEFAULT_SIZE;
    private int capacity = DEFAULT_CAPACITY;
    private Object[] list = new Object[capacity];

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        list[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index);
        }

        ensureCapacity(size + 1);

        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> other) {
        ensureCapacity(size + other.size());

        for (int i = 0; i < other.size(); i++) {
            list[size + i] = other.get(i);
        }
        size += other.size();
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed = (T) list[index];

        System.arraycopy(list, index + 1, list, index, size - index - 1);

        list[--size] = null;

        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? list[i] == null : element.equals(list[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can`t delete non existing element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= capacity) {
            return;
        }
        capacity = Math.max((int) (capacity * GROW_BY), minCapacity);
        Object[] newList = new Object[capacity];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index);
        }
    }
}
