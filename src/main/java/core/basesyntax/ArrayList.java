package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] values;
    private int size;
    private final int currentLength;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_SIZE];
        currentLength = values.length;
    }

    @Override
    public void add(T value) {
        if (size >= currentLength) {
            resize();
        }
        values[size] = value;
        size++;
    }

    private void resize() {
        T[] newValues = (T[]) new Object[size * 3 / 2 + 1];
        System.arraycopy(values, 0, newValues, 0, size);
        values = newValues;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is not valid");
        }
        if (size >= currentLength) {
            resize();
        }
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        ++size;
    }

    @Override
    public void addAll(List<T> list) {
        if (size >= currentLength) {
            resize();
        }
        int counter = size;
        for (int i = 0; i < list.size(); i++) {
            values[counter] = list.get(i);
            size++;
            counter++;
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return values[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("index is not valid");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is not valid");
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is not valid");
        }
        T removedElement = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index >= size || index < 0) {
            throw new NoSuchElementException("There is no element like this: " + element + " in storage");
        }
        return remove(index);
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] == null ? element == null : values[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
