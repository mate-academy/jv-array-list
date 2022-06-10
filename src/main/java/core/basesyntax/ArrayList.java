package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        resizeArray();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("This index doesn't exist");
        }
        resizeArray();

        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) {
        for (int h = 0; h < list.size(); h++) {
            add(list.get(h));
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
        T removedValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int l = 0; l < size; l++) {
            if (element == values[l] || element != null && element.equals(values[l])) {
                return remove(l);
            }
        }
        throw new NoSuchElementException("There is no such value in array " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArray() {
        if (size == values.length) {
            int newSize = (size + (size >> 1));
            T[] resizeElement = (T[]) new Object[newSize];
            System.arraycopy(values, 0, resizeElement, 0, values.length);
            values = resizeElement;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " doesn't exist in list of size " + size);
        }
    }
}
