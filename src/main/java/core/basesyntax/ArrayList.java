package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int arraySize = 0;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        values[arraySize++] = (T) value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        grow();
        System.arraycopy(values, index, values, index + 1, arraySize - index);
        values[index] = (T) value;
        arraySize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T removedElement = values[index];
        if (index != values.length - 1) {
            System.arraycopy(values, index + 1, values,
                    index, arraySize - 1);
        } else {
            values[index] = null;
        }
        arraySize--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if (element == null && values[i] == null || values[i] != null
                        && values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element");
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    private void grow() {
        if (values.length == arraySize) {
            Object newArray = new Object[(int) (size() * 1.5)];
            System.arraycopy(values, 0, newArray, 0, values.length);
            values = (T[]) newArray;
        }
    }

    public void validateIndex(int index) {
        if (index < 0 || index >= arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }
}


