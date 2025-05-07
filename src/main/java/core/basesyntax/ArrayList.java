package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] values;
    private int size;

    public ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (values.length == size) {
            grow();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (values.length == size) {
            grow();
        }
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
        chekIndex(index);
        return (T)values[index];
    }

    @Override
    public void set(T value, int index) {
        chekIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        chekIndex(index);
        T value = (T) values[index];
        size--;
        System.arraycopy(values, index + 1, values, index, size - index);
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                T value = (T) values[i];
                remove(i);
                return value;
            }
        }
        throw new NoSuchElementException("Doesn't exist elements");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T [] grow() {
        T[] sourceArray = (T[]) values;
        values = new Object[size + (size >> 1)];
        System.arraycopy(sourceArray, 0, values, 0, size);
        return sourceArray;
    }

    private void chekIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index + "out of size" + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index + "out of size" + size);
        }
    }
}
