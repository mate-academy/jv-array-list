package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int capacity;
    private int size;
    private T[] array;

    public ArrayList() {
        this.capacity = 10;
        this.array = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size >= capacity) {
            grow(capacity);
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndex(index);
        }
        if (size == array.length) {
            grow(size);
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
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
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        int newSize = size - 1;
        T element = array[index];
        System.arraycopy(array, index + 1, array, index, newSize - index);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        int index = getIndexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("Couldn't find file '" + element + "'!");
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

    private void grow(int minCapacity) {
        capacity = minCapacity + (minCapacity >> 1);
        T[] oldArray = array;
        array = (T[]) new Object[capacity];
        System.arraycopy(oldArray, 0, array, 0, minCapacity);
    }

    public int getIndexOf(T element) {
        for (int i = 0; i < size; i++) {
            if ((array[i] == element) || array[i] != null && array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds!");
        }
    }
}
