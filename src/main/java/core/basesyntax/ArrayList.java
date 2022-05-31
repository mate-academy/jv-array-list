package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private Object[] data;
    private int size;

    public ArrayList() {
        this.data = new Object[INITIAL_SIZE];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        increase();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndexInBounds(index);
        increase();
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
    public T get(int index) {
        checkIndexInBounds(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexInBounds(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexInBounds(index);
        final T oldValue = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < data.length; i++) {
            if (element == data[i] || element != null && element.equals(data[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increase() {
        if (size == data.length) {
            final Object[] buffer = new Object[data.length * 3 / 2];
            System.arraycopy(data, 0, buffer, 0, data.length);
            data = buffer;
        }
    }

    private void checkIndexInBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
