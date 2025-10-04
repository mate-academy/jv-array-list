package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 1;
    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.data = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            growIfArrayFull();
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkRangeForAdd(index);

        if (size == data.length) {
            growIfArrayFull();
        }
        if (size - index > 0) {
            System.arraycopy(data, index, data, index + 1, size - index);
        }
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (data.length - size < list.size()) {
            growIfArrayFull();
        }

        for (int i = 0; i < list.size(); i++) {
            data[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkRange(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkRange(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRange(index);

        T oldValue = data[index];

        if (size - index - 1 > 0) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }

        data[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? data[i] == null
                    : element.equals(data[i])) {
                System.arraycopy(data, i + 1, data, i, size - i - 1);
                size--;
                data[size] = null;
                return element;
            }
        }
        throw new NoSuchElementException("Element not found in the list: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void growIfArrayFull() {
        int newCapacity = data.length + (data.length >> GROWTH_FACTOR);
        T[] bufferArr = data;
        if (newCapacity < data.length) { // Check for overflow
            newCapacity = Integer.MAX_VALUE;
        }
        this.data = (T[]) new Object[newCapacity];
        System.arraycopy(bufferArr, 0, this.data, 0, bufferArr.length);
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }

    private void checkRangeForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }
}
