package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_STEP = 1.5;
    private int size;
    private T[] data;

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        this.data = (T[]) new Object[capacity];
    }

    public ArrayList() {
        this.data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void grow() {
        T[] arrayCopy = (T[]) new Object[(int) (data.length * INCREASE_STEP)];
        System.arraycopy(data,0,arrayCopy,0,data.length);
        data = arrayCopy;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Passed index is invalid");
        }
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            grow();
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        if (size == data.length) {
            grow();
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removedElement = data[index];
        System.arraycopy(data, index + 1, data, index, size - 1 - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < this.size; i++) {
            if (data[i] == element || data[i] != null && data[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in list" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
}
