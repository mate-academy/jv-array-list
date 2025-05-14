package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void ensureCapacity() {
        if (array.length == Integer.MAX_VALUE) {
            throw new OutOfMemoryError("Cannot expand array any further");
        }
        int newCapacity;
        if (array.length > Integer.MAX_VALUE / 2) {
            newCapacity = Integer.MAX_VALUE;
        } else {
            newCapacity = Math.max(array.length + 1,
                    (int) (array.length * GROWTH_FACTOR));
        }
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            ensureCapacity();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is negative");
        } else if (index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        } else if (index == size) {
            add(value);
        } else {
            if (size == array.length) {
                ensureCapacity();
            }
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("Cannot add null list");
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is negative");
        } else if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        } else {
            array[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        } else {
            T result = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            array[--size] = null;
            return result;
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && array[i] == null)
                    || (element != null && element.equals(array[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
