package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double EXTENSION_FACTOR = 1.5;
    private static final int NOT_FOUND_INDEX = -1;
    private int size;
    private T[] data;

    public ArrayList() {
        size = 0;
        data = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            resize();
        }

        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexExclusive(index);

        if (size == data.length) {
            resize();
        }

        System.arraycopy(data, index, data, index + 1, data.length - 1 - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.isEmpty()) {
            return;
        }

        int newCapacity = size + list.size();
        while (data.length < newCapacity) {
            resize();
        }

        T[] listData = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listData[i] = list.get(i);
        }

        System.arraycopy(listData, 0, data, size, list.size());
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        return shrinkArray(index);
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        if (index == NOT_FOUND_INDEX) {
            throw new NoSuchElementException("Element is absent in this list");
        }

        return shrinkArray(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        final int newCapacity = (int) (size * EXTENSION_FACTOR);
        T[] temp = (T[]) new Object[newCapacity];
        System.arraycopy(data, 0, temp, 0, size);
        data = temp;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                + " out of bounds for length " + size);
        }
    }

    private int getIndex(T element) {
        int index = NOT_FOUND_INDEX;

        for (int i = 0; i < size; i++) {
            if (element == data[i] || element != null && element.equals(data[i])) {
                index = i;
                break;
            }
        }

        return index;
    }

    private T shrinkArray(int index) {
        final T result = data[index];
        data[index] = null;

        if (index != size - 1) {
            System.arraycopy(data, index + 1, data, index, data.length - index - 1);
        }

        size--;

        return result;
    }

    private void checkIndexExclusive(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                + " out of bounds for length " + size);
        }
    }
}
