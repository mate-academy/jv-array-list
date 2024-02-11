package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MULTIPLYER = 1.5;
    private static final int ONE_ELEMENT = 1;
    private Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity(ONE_ELEMENT);
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index" + index
                    + "is out of bounds");
        }
        ensureCapacity(ONE_ELEMENT);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(list.size());
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
        checkIndex(index);
        T removedElement = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(data[i])) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int numberOfElements) {
        if (size + numberOfElements <= data.length) {
            for (Object object : data) {
                if (object == null) {
                    return;
                }
            }
        }
        double newCapacity = (double) data.length * MULTIPLYER;
        Object[] newData = new Object[(int) newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index" + index
                    + "is out of bounds");
        }
    }
}
