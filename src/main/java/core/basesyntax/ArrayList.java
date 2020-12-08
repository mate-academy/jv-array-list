package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MULTIPLAYER = 1.5;
    private Object[] data;
    private int size = 0;

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        data = new Object[capacity];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size + 1);
        if (data.length == size) {
            expand();
        }
        if (size > index) {
            System.arraycopy(data, index, data, index + 1, size - index);
        }
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, size);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T item = get(index);
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return item;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(get(i), t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(String.format("Element %s not found.", t));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void expand() {
        Object[] array = data;
        data = new Object[(int) (data.length * MULTIPLAYER)];
        System.arraycopy(array, 0, data, 0, array.length);
    }

    private static void checkIndex(int index, int length) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
