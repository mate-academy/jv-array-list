package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 0;
    private static final int DEFAULT_SIZE = 0;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = DEFAULT_SIZE;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = array.length + (array.length >> 1);
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        validateIndex(index);
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + list.size());
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index + 1);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index + 1);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        T oldValue = get(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && array[i] == null)
                    || (element != null && element.equals(array[i]))) {
                return remove(i);
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

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " out of bound!!"
                    + " Current size: " + size);
        }
    }
}
