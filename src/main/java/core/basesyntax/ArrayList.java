package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] values;

    public ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        indexChecking(index);
        growIfArrayFull();

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
        indexChecking(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        indexChecking(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        indexChecking(index);

        T elementByIndex = (T) values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;

        return elementByIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null && element.equals(values[i])
                    || element == null && values[i] == null) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("There is no element "
                + element.toString() + " in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size == values.length) {
            Object[] newList = new Object[values.length + (values.length >> 1)];
            System.arraycopy(values, 0, newList, 0, size);
            values = newList;
        }
    }

    private void indexChecking(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be less than zero");
        }
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index should be less than list's size "
                    + size);
        }
    }
}
