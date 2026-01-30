package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final String IS_OUT_OF_BOUNDS = " is out of bounds";
    public static final double INCREASE_FACTOR = 1.5;
    private int capacity;
    private int size = 0;
    private T[] data;

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("wrong capacity");
        }
        data = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkBound(index);
        ensureCapacity();
        System.arraycopy(data, index,
                data, index + 1,
                size - index);
        data[index] = value;
        size++;
    }

    private void ensureCapacity() {
        ensureCapacity(1);
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int step) {
        int minCapacity = size + step;
        if (minCapacity <= capacity) {
            return;
        }
        capacity = (int) (INCREASE_FACTOR * capacity);
        capacity = Math.max(capacity, minCapacity);
        T[] temp = data;
        data = (T[]) new Object[capacity];
        System.arraycopy(temp, 0, data, 0, size);
    }

    @Override
    public void addAll(List<T> list) {
        int numNew;
        if (list == null || (numNew = list.size()) == 0) {
            return;
        }
        ensureCapacity(numNew);
        for (int i = 0; i < numNew; i++) {
            data[size + i] = list.get(i);
        }
        size += numNew;
    }

    @Override
    public T get(int index) {
        checkBound(index);
        return data[index];
    }

    private void checkBound(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(index + IS_OUT_OF_BOUNDS);
        }
    }

    @Override
    public void set(T value, int index) {
        checkBound(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        int newSize = size - 1;
        checkBound(index);
        T e = data[index];
        if (newSize > 0) {
            System.arraycopy(data, index + 1, data, index, newSize - index);
        }
        size = newSize;
        return e;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            T e = data[i];
            if (element == null && e == null) {
                return remove(i);
            } else if (e != null && e.equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " not found in the list");
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
