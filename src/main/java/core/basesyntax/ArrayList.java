package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double INCREASE_COEFICIENT = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;
    private int capacity;

    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        array = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (!isEnoughSpace()) {
            relocateArray();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
        if (index == size) {
            add(value);
            return;
        }
        if (!isFreeSpace()) {
            relocateArray();
        }
        System.arraycopy(array, index, array, index + 1,
                size - index);

        array[index] = value;
        size++;
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
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T deletedValue = array[index];
        System.arraycopy(array, index + 1, array, index,
                size - 1 - index);

        size--;
        return deletedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i]
                    || element != null && element.equals(array[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    private boolean isEnoughSpace() {
        return size != capacity;
    }

    private int freeSpace() {
        return capacity - size;
    }

    private void relocateArray() {
        increaseCapacity();
        T[] newArray = (T[]) new Object[capacity];
        if (size >= 0) {
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void increaseCapacity() {
        capacity = (int) (capacity * INCREASE_COEFICIENT);
    }

    private void checkIndex(int index) {
        if (!isIndexInRange(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
    }

    private boolean isFreeSpace() {
        return freeSpace() != 0;
    }

    private boolean isIndexInRange(int index) {
        return index >= 0 && index < size;
    }
}

