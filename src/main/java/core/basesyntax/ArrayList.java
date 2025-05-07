package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final float INCREASE_FACTOR = 1.5f;

    private int size;
    private int capacity = 10;
    private T[] list;

    public ArrayList() {
        this.list = (T[]) new Object[capacity];
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
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T removedElement = list[index];
        shiftAllRightFromIndex(index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        T removedElement;
        for (int i = 0; i < size; i++) {
            if (element == null && list[i] == null
                    || list[i] != null && list[i].equals(element)) {
                removedElement = list[i];
                remove(i);
                return removedElement;
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
        list = (T[]) new Object[capacity];
        System.arraycopy(tempList, 0, list, 0, size);
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
        System.arraycopy(list, index, list, index + 1, size - index);
    }

    private void shiftAllRightFromIndex(int index) {
        System.arraycopy(list, index + 1, list, index, size - index - 1);
        list[size - 1] = null;
    }
}
