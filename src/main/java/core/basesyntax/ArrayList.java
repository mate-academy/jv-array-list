package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWN_FACTOR = 1.5;
    private int size = 0;
    private T [] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index for add operation");
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
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
        checkIndex(index, "Invalid index for get operation");
        return elements[index];

    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, "Invalid index for set operation");
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, "Invalid index for remove operation");
        final T removedValue = elements[index];
        int numToCopy = size - index - 1;
        if (numToCopy > 0) {
            System.arraycopy(elements, index + 1, elements, index, numToCopy);
        }
        elements[size - 1] = null;
        size--;
        return removedValue;
    }

    public T remove(T element) {
        int index = indexOf(element);
        if (index != -1) {
            return remove(index);
        } else {
            throw new NoSuchElementException("Element not found, you can't remove it! " + element);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * GROWN_FACTOR);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private void checkIndex(int index, String errorMessage) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(errorMessage);
        }
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && elements[i] == null) {
                return i;
            } else if (element != null && element.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }
}

