package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_CAPACITY_COEFFICIENT = 1.5;
    private Object[] data;
    private int size;

    public ArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= data.length) {
            data = grow();
        }
        data[size] = value;
        size += 1;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAddedElement(index);
        if (size >= data.length) {
            data = grow();
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        data[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T elementToRemove = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        int indexToRemove = getIndexByElement(element);
        if (indexToRemove == -1) {
            throw new NoSuchElementException("No element found by value " + element);
        }
        return this.remove(indexToRemove);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index
                    + " is out of bounds for the size "
                    + (size));
        }
    }

    private void checkIndexForAddedElement(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index
                    + " is out of bounds for the size "
                    + (size));
        }
    }

    private Object[] grow() {
        int newSize = (int) (data.length * GROW_CAPACITY_COEFFICIENT);
        Object[] newData = new Object[newSize];
        System.arraycopy(data, 0, newData, 0, size);
        return newData;
    }

    private int getIndexByElement(T element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element || data[i] != null && data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}
