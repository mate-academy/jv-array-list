package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] dataElements;
    private int size = 0;

    public ArrayList() {
        dataElements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == dataElements.length) {
            grow();
        }
        dataElements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);

        if (size == dataElements.length) {
            grow();
        }

        System.arraycopy(dataElements, index, dataElements, index + 1, size - index);
        dataElements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        ensureCapacity(size + list.size());

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return dataElements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        dataElements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedIndex = dataElements[index];

        System.arraycopy(dataElements, index + 1, dataElements, index, size - index - 1);
        dataElements[--size] = null;

        return removedIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, dataElements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element: " + element + " in current Array.");
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
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out bounds.");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out bounds.");
        }
    }

    private void grow() {
        int newCapacity = dataElements.length + (dataElements.length >> 1);
        resize(newCapacity);
    }

    private void ensureCapacity(int minCapacity) {
        int newCapacity = dataElements.length + minCapacity;
        resize(newCapacity);
    }

    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];

        System.arraycopy(dataElements, 0, newArray, 0, dataElements.length);

        dataElements = newArray;
    }
}
