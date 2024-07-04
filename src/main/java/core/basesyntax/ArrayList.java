package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private static final double RESIZE_FACTOR = 1.5;
    private static final int ELEMENT_NOT_FOUND = -1;
    private int size;
    private Object[] array = new Object[CAPACITY];

    @Override
    public void add(T value) {
        growIfArrayFull();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for size " + size);
        }
        growIfArrayFull();

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() + size > array.length) {
            while (list.size() + size > array.length) {
                resize();
            }
        }
        for (int i = 0; i < list.size(); i++) {
            array[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkIndexOutOfBounds(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexOutOfBounds(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexOutOfBounds(index);
        T removedElement = (T) array[index];
        shiftLeftFromIndex(index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = ELEMENT_NOT_FOUND;
        for (int i = 0; i < size; i++) {
            if (array[i] == element || (element != null && element.equals(array[i]))) {
                index = i;
                break;
            }
        }
        if (index == ELEMENT_NOT_FOUND) {
            throw new NoSuchElementException("Element not found");
        }

        T removedElement = (T) array[index];
        shiftLeftFromIndex(index);
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexOutOfBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for size " + size);
        }
    }

    private void resize() {
        int newCapacity = (int) (array.length * RESIZE_FACTOR);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void growIfArrayFull() {
        if (size == array.length) {
            resize();
        }
    }

    private void shiftLeftFromIndex(int index) {
        checkIndexOutOfBounds(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        array[--size] = null;
    }
}
