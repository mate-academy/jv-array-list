package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_RATE = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growArray();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Unable to add "
                    + value + " at the " + index + " index");
        }
        growArray();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
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
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removeValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removeValue;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (element == null ? values[i] == null : element.equals(values[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("This value does not exist " + element);
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean checkIndex(int index) throws ArrayListIndexOutOfBoundsException {
        if (index != 0
                && index >= size
                || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " does not exist, size of the ArrayList "
                    + size);
        }
        return true;
    }

    private void growArray() {
        if (size == values.length) {
            T[] newArrayList = (T[]) new Object [(int) (values.length * GROW_RATE)];
            System.arraycopy(values, 0, newArrayList, 0, values.length);
            values = newArrayList;
        }
    }
}
