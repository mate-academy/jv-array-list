package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] data;

    public ArrayList() {
        size = 0;
        data = new Object[DEFAULT_CAPACITY];
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
        checkIndex(index, size);
        if (size == data.length) {
            grow();
        }
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
        checkIndex(index, size - 1);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size - 1);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size - 1);
        T removedElement = (T) data[index];
        if (index != size - 1) {
            System.arraycopy(data, index + 1, data, index, size - index);
        }
        data[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = findIndex(element);
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

    private void checkIndex(int index, int size) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private int findIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (element == data[i]
                    || element != null && element.equals(data[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("No such element in ArrayList");
    }

    private void grow() {
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] resizedCopy = new Object[newCapacity];
        System.arraycopy(data, 0, resizedCopy,0, size);
        data = resizedCopy;
    }
}
