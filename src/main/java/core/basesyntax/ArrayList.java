package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (index == size) {
            add(value);
            return;
        }
        grow();
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
        T value = values[index];
        size--;
        System.arraycopy(values, index + 1, values, index, size - index);
        return value;
    }

    @Override
    public T remove(T element) {
        int index;
        for (index = 0; index < size; index++) {
            if (values[index] == element
                    || (values[index] != null
                    && values[index].equals(element))) {
                return remove(index);
            }
        }
        throw new NoSuchElementException("Element " + element + " does not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index" + index + "out of size" + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index" + index + "out of size" + size);
        }
    }

    private void grow() {
        if (values.length == size) {
            T[] tempArray = (T[]) new Object[values.length + (values.length >> 1)];
            System.arraycopy(values, 0, tempArray, 0, size);
            values = tempArray;
        }
    }

}

