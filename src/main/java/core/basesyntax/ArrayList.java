package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    private T[] elements;
    private int size;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacityIfFull();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        ensureCapacityIfFull();
        if (index < size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }
        elements[index] = value;
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
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T currentElement = get(index);
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
        return currentElement;
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        if (index < 0) {
            throw new NoSuchElementException("Element not found: " + element);
        }

        return remove(index);
    }

    private int getIndex(T value) {
        for (int i = 0; i < size; i++) {
            if (value == null) {
                if (elements[i] == null) {
                    return i;
                }
            } else {
                if (value.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureCapacityIfFull() {
        if (size == elements.length) {
            grow();
        }
    }

    private void grow() {
        int newCapacity = (int) (elements.length * GROWTH_FACTOR);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " out of bounds for length " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        return "ArrayList{"
                + "elements="
                + Arrays.toString(elements)
                + ", size="
                + size + '}';
    }
}
