package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private T[] values;
    private int elementsAdded;
    private boolean resize;

    public ArrayList() {
        values = (T[])new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        int index = checkIndex();
        if (index >= values.length) {
            resize = true;
            resize();
        }
        values[index] = value;
        elementsAdded++;
    }

    @Override
    public void add(T value, int index) {
        if (index > checkIndex() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size());
        }
        if (index == checkIndex()) {
            if (index >= values.length) {
                resize = true;
                resize();
            }
            add(value);
        } else {
            if (size() == values.length) {
                resize = true;
                resize();
            }
            T[] tmp = (T[]) new Object[size()];
            System.arraycopy(values, index, tmp, 0, size() - index);
            values[index] = value;
            System.arraycopy(tmp, 0, values, index + 1, size() - index);
            elementsAdded++;
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
        if (index >= 0 && index < size()) {
            return values[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index " + index
                + " out of bounds for length " + size());
    }

    @Override
    public void set(T value, int index) {
        if (size() == 0 || index >= checkIndex() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size());
        } else {
            values[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        T value;
        if (size() == 0 || index >= checkIndex() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size());
        } else {
            T[] tmp = (T[]) new Object[values.length];
            System.arraycopy(values, index + 1, tmp, 0, size() - index - 1);
            value = values[index];
            System.arraycopy(tmp, 0, values, index, size() - index);
            elementsAdded--;
        }
        if (values.length - size() >= 5) {
            resize = false;
            resize();
        }
        return value;
    }

    @Override
    public T remove(T element) {
        T result;
        for (int i = 0; i < size(); i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                result = values[i];
                remove(i);
                return result;
            }
        }
        throw new NoSuchElementException("There is no such element "
                + "<" + element + ">" + " present in ArrayList");
    }

    @Override
    public int size() {
        return elementsAdded;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private int checkIndex() {
        return size();
    }

    private void resize() {
        int newCapacity;
        if (resize) {
            newCapacity = values.length + (INITIAL_CAPACITY >> 1);
        } else {
            newCapacity = values.length - (INITIAL_CAPACITY >> 1);
        }
        T[] tmp = (T[])new Object[newCapacity];
        System.arraycopy(values, 0, tmp, 0, size());
        values = (T[])new Object[newCapacity];
        System.arraycopy(tmp, 0, values, 0, size());
    }
}
