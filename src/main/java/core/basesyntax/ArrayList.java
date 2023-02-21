package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int size;

    public ArrayList() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(E value) {
        if (isStorageFull()) {
            grow();
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(E value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element: " + value
                    + " on the position: " + index);
        } else if (isStorageFull()) {
            grow();
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<E> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public E get(int index) {
        checkIndexBounds(index);
        return data[index];
    }

    @Override
    public void set(E value, int index) {
        checkIndexBounds(index);
        data[index] = value;
    }

    @Override
    public E remove(int index) {
        checkIndexBounds(index);
        E deletingData = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return deletingData;
    }

    @Override
    public E remove(E element) {
        for (int i = 0; i < size; i++) {
            if (areEqual(data[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't remove non-existing element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isStorageFull() {
        return size() == data.length;
    }

    private void grow() {
        int newCapacity = (data.length >> 1) + data.length;
        E[] biggerData = (E[]) new Object[newCapacity];
        System.arraycopy(data, 0, biggerData, 0, data.length);
        data = biggerData;
    }

    private void checkIndexBounds(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index : " + index + " is out of bounds.");
        }
    }

    private boolean areEqual(E a, E b) {
        return a == b || a != null && a.equals(b);
    }
}
