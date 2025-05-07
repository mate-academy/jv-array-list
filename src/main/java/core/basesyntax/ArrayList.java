package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH = 1.5;
    private T[] array;
    private int size = 0;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkSize(index);
        ensureCapacity(size + 1);
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
        checkBounds(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkBounds(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBounds(index);
        T removed = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        if (element != null) {
            for (int i = 0; i < size; i++) {
                if (element.equals(array[i])) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("Not found this element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newSize = (int) (array.length * GROWTH);
            if (newSize < minCapacity) {
                newSize = minCapacity;
            }
            T[] newArray = (T[]) new Object[newSize];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    private void checkSize(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
