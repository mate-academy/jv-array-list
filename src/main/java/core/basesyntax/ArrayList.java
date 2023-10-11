package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_PARAMETER = 1.5;
    private Object[] data;
    private int size;

    public ArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            grow();
        }
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }

        if (size == data.length) {
            grow();
        }

        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
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
        final T removedValue = get(index);
        if (size > 1) {
            for (int i = index; i < size - 1; i++) {
                data[i] = data[i + 1];
            }
        }
        data[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && data[i] == null) 
                    || (element != null && element.equals(data[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void grow() {
        int newCapacity = (int) (data.length * GROWTH_PARAMETER);
        Object[] newData = new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }
}
