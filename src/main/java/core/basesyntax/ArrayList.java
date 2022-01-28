package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            grow();
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == data.length) {
            grow();
        }
        if (index >= 0 && index <= size) {
            System.arraycopy(data, index, data, index + 1, size - index);
            data[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid.");
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() >= size) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            data[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData(index);
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T elementToRemove = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < data.length; i++) {
            if (element == data[i] || element != null && element.equals(data[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element");
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
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + oldCapacity / 2;
        data = Arrays.copyOf(data, newCapacity);
    }

    private T elementData(int index) {
        return data[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || (index > size - 1 && index != 0)
                || (index == size && index > data.length)) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid.");
        }
    }
}
