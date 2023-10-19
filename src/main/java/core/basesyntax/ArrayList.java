package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, false);
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + list.size());
        for (int i = 0; i < list.size(); i++) {
            array[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, true);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, true);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, true);
        T removedElement = (T) array[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        array[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(Object element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(array[i])) {
                    return remove(i);
                }
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

    private void checkIndex(int index, boolean inclusive) {
        if (inclusive) {
            if (index < 0 || index >= size) {
                throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
            }
        } else {
            if (index < 0 || index > size) {
                throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
            }
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = Math.max(array.length * 3 / 2 + 1, minCapacity);
            Object[] newArray = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }
}
