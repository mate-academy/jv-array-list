package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static Object[] ARRAY;
    private int capacity = 10;
    private int size = 0;

    public ArrayList() {
        ARRAY = new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            ARRAY = grow();
        }
        ARRAY[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            ARRAY = grow();
        }
        System.arraycopy(ARRAY, index, ARRAY, index + 1, size - index);
        ARRAY[index] = value;
        size++;
    }

    private Object[] grow() {
        capacity = capacity + capacity / 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(ARRAY, 0, newArray, 0, size);
        return newArray;
    }

    @Override
    public void addAll(List<T> list) {
        while (!list.isEmpty()) {
            add(list.remove(0));
        }
    }

    @Override
    public T get(int index) {
        if (index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) ARRAY[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        ARRAY[index] = value;
    }

    @Override
    public T remove(int index) {
        T removed = get(index);
        System.arraycopy(ARRAY, index + 1, ARRAY, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public T remove(T t) {
        T element = null;
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (ARRAY[i] == null && t == null || ARRAY[i] != null && ARRAY[i].equals(t)) {
                element = remove(i);
                count++;
            }
        }
        if (count == 0) {
            throw new NoSuchElementException();
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
