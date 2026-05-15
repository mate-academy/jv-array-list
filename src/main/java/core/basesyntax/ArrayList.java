package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    private T[] data;
    private int size;

    public ArrayList() {
        this.data = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public ArrayList(T[] elementData, int size) {
        if (elementData == null) {
            throw new IllegalArgumentException("elementData is null");
        }
        if (size < 0 || size > elementData.length) {
            throw new IllegalArgumentException("Invalid size: " + size);
        }
        this.data = elementData;
        this.size = size;
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
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " is out of bounds for size " + size);
        }

        if (size == data.length) {
            growIfArrayFull();
        }

        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("list is null");
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
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

        final T removed = data[index];

        int elementsToMove = size - index - 1;
        if (elementsToMove > 0) {
            System.arraycopy(data, index + 1, data, index, elementsToMove);
        }

        data[size - 1] = null;
        size--;

        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((data[i] == null && element == null)
                    || (data[i] != null && data[i].equals(element))) {
                T removed = data[i];
                remove(i);
                return removed;
            }
        }
        throw new NoSuchElementException("element not found");
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
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " is out of bounds for size " + size);
        }
    }

    private void growIfArrayFull() {
        int newCapacity = (int) (data.length * GROWTH_FACTOR);
        T[] newData = (T[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }
}
