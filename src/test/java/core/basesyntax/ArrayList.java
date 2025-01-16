package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;

    public ArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(E value) {
        ensureCapacity();
        elements[size++] = value;
    }

    public void add(E value, int index) {
        validateIndexForAdd(index);
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    public void addAll(ArrayList<E> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        for (int i = 0; i < list.size; i++) {
            add(list.get(i));
        }
    }

    public E remove(int index) {
        validateIndex(index);
        E removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;

        return removedElement;
    }

    public E remove(E value) {
        for (int i = 0; i < size; i++) {
            if ((value == null && elements[i] == null) || (value != null && value.equals(elements[i]))) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("Element not found: " + value);
    }

    public E get(int index) {
        validateIndex(index);

        return elements[index];
    }

    public void set(E value, int index) {
        validateIndex(index);
        elements[index] = value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(elements.length * 2, minCapacity);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds for add: " + index);
        }
    }
}
