package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double COEFFICIENT = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void resize() {
        int newCapacity = (int) (values.length * COEFFICIENT);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(values, 0, newArray, 0, size);
        values = newArray;
    }

    @Override
    public void add(T value) {
        checkResize();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, value);
        checkResize();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("List is null");
        }
        while (size + list.size() > values.length) {
               resize();
        }
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
        T removedValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && values[i] == null
                    || element != null && element.equals(values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is not the element");
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
            throw new ArrayListIndexOutOfBoundsException("Index is wrong");
        }
    }

    private void checkIndex(int index, T value) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add " + value + " to " + index);
        }
    }

    private void checkResize() {
        if (size == values.length) {
            resize();
        }
    }

}
