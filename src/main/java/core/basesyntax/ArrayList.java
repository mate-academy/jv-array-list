package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;

    private static final double GROW_FACTOR = 1.5;

    private int size;
    private T[] array;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative: "
                    + initialCapacity);
        }
        this.array = (T[]) new Object[initialCapacity];
        this.size = 0;
    }

    private void grow() {
        int newCapacity = (int) (array.length * GROW_FACTOR);

        T[] newArray = (T[]) new Object[newCapacity];

        System.arraycopy(array, 0, newArray, 0, size);

        array = newArray;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index for add: "
                    + index + ", size: " + size);
        }

        if (size == array.length) {
            grow();
        }
        System.arraycopy(array, index, array, index + 1, size - index);

        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new ArrayListIndexOutOfBoundsException("Cannot add null list");
        }

        while (size + list.size() > array.length) {
            grow();
        }

        for (int i = 0; i < list.size(); i++) {
            array[size++] = list.get(i);
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

        T removedElement = array[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }

        array[--size] = null;

        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            T currentElement = array[i];
            if (element == currentElement || (element != null && element.equals(currentElement))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found in list.");
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
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + ", size: " + size);
        }
    }
}

