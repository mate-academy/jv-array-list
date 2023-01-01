package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_SIZE];
    }

    public ArrayList(int initialCapacity) {
        values = (T[]) new Object[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T object) {
        resize();
        values[size++] = object;
    }

    @Override
    public void add(T object, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound!");
        }
        resize();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = object;
        ++size;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        checkInBounds(index);
        return (T) values[index];
    }

    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (values[i] == value || (values[i] != null && values[i].equals(value))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T remove(int index) {
        checkInBounds(index);
        T removedObject = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[--size] = null;
        return removedObject;
    }

    @Override
    public T remove(T value) {
        int index = indexOf(value);
        if (index == -1) {
            throw new NoSuchElementException("There is no element like this!");
        }
        checkInBounds(index);
        remove(index);
        return value;
    }

    public void set(T value, int index) {
        checkInBounds(index);
        values[index] = value;
    }

    private void resize() {
        if (size == values.length) {
            T[] data = values;
            values = (T[]) new Object[size * 3 / 2];
            System.arraycopy(data, 0, values, 0, size);
        }
    }

    private void checkInBounds(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index" + index + " is out of bound!");
        }
    }
}
