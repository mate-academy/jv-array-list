package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MIN_CAPACITY = 10;
    private int currentCapacity;
    private Object[] data;
    private int size;

    public ArrayList() {
        this.data = new Object[MIN_CAPACITY];
        currentCapacity = MIN_CAPACITY;
        this.size = 0;
    }

    @Override
    public void add(T value) {
        grow();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        grow();
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
        checkIndex(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        T value = get(index);
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (compareElements((T) data[i], element)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Element not found: " + element);
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (size + 1 > currentCapacity) {
            int newCapacity = currentCapacity + (currentCapacity >> 1);
            Object[] newData = new Object[newCapacity];
            System.arraycopy(data, 0, newData, 0, data.length);
            currentCapacity = newCapacity;
            data = newData;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    private boolean compareElements(T currentEl, T searchedEl) {
        if (currentEl == null) {
            return null == searchedEl;
        }
        return currentEl.equals(searchedEl);
    }
}
