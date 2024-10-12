package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] dataElement;
    private int size = 0;

    public ArrayList() {
        dataElement = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == dataElement.length) {
            grow();
        }
        dataElement[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);

        if (size == dataElement.length) {
            grow();
        }

        System.arraycopy(dataElement, index, dataElement, index + 1, size - index);
        dataElement[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.size() == 0) {
            return;
        }

        ensureCapacity(size + list.size());

        for (int i = 0; i < list.size(); i++) {
            dataElement[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return dataElement[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        dataElement[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedIndex = dataElement[index];

        System.arraycopy(dataElement, index + 1, dataElement, index, size - index - 1);
        dataElement[--size] = null;

        return removedIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, dataElement[i])) {
                return remove(i);
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

    private void grow() {
        int newCapacity = dataElement.length + (dataElement.length >> 1);
        dataElement = Arrays.copyOf(dataElement, newCapacity);
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

    private void ensureCapacity(int minCapacity) {
        int newCapacity = minCapacity + size;
        dataElement = Arrays.copyOf(dataElement, newCapacity);
    }
}
