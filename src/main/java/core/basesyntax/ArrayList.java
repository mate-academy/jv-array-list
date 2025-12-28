package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROW_BY = 1.5;
    private int size = 0;
    private int capacity = 10;
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

        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
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

        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
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
        throw new NoSuchElementException();
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
