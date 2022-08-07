package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double SIZE_MULTIPLIER = 1.5;
    private static final String WRONG_INDEX = "Index is out of list size";
    private T[] values;
    private int size;
    private int capacity;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            increaseCapacity();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(WRONG_INDEX);
        }
        if (index == size) {
            add(value);
        } else {
            while (capacity < size + 1) {
                increaseCapacity();
            }
            System.arraycopy(values, index, values, index + 1, size - index);
            values[index] = value;
            size++;
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
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T value = (T) values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element didn`t found");
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
        capacity *= SIZE_MULTIPLIER;
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(values, 0, temp, 0, values.length);
        values = temp;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(WRONG_INDEX);
        }
    }
}
