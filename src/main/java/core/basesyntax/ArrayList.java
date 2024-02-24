package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float INCREASE_FACTOR = 1.5f;

    private int size;
    private int capacity = DEFAULT_CAPACITY;
    private Object[] list;

    public ArrayList() {
        this.list = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdding(index);
        if (size >= capacity) {
            increaseCapacity();
        }
        if (index < size) {
            shiftAllLeftFromIndex(index);
        }
        list[index] = value;
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
        validateIndex(index);
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T removedEl = (T) list[index];
        shiftAllRightFromIndex(index);
        size--;
        return removedEl;
    }

    @Override
    public T remove(T element) {
        T removedEl;
        for (int i = 0; i < size; i++) {
            if (element == null && list[i] == null
                    || list[i] != null && list[i].equals(element)) {
                removedEl = (T) list[i];
                remove(i);
                return removedEl;
            }
        }
        throw new NoSuchElementException("No such element " + element);
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
        Object[] tempList = list;

        capacity *= INCREASE_FACTOR;
        list = new Object[capacity];

        for (int i = 0; i < size; i++) {
            list[i] = tempList[i];
        }
    }

    private void validateIndex(int index) {
        validateIndexToBound(index, size);
    }

    private void validateIndexForAdding(int index) {
        validateIndexToBound(index, size + 1);
    }

    private void validateIndexToBound(int index, int bound) {
        if (index < 0 || index >= bound) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " out of bounds for length " + size
            );
        }
    }

    private void shiftAllLeftFromIndex(int index) {
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
    }

    private void shiftAllRightFromIndex(int index) {
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[size - 1] = null;
    }
}
