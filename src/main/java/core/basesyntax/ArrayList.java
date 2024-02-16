package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final double RESIZE_FACTOR = 1.5;

    private T[] container;
    private int size;

    public ArrayList() {
        container = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growArray(size + 1);
        container[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        growArray(size + 1);
        insertElement(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return container[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        container[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T removedElement = container[index];
        removeElement(index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementsAreEqual(element, container[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't find element while removing");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growArray(int minCapacity) {
        if (minCapacity <= container.length) {
            return;
        }
        T []copy = (T[]) new Object[(int)(container.length * RESIZE_FACTOR)];
        System.arraycopy(container, 0, copy, 0, size);
        container = copy;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index value of " + index
            + " for size " + size);
        }
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index of " + index
                    + " value while adding for size " + size);
        }
    }

    private void insertElement(T value, int index) {
        T[] copy = (T[]) new Object[container.length];
        System.arraycopy(container, 0, copy, 0, index);
        copy[index] = value;
        System.arraycopy(container, index, copy, index + 1, size - index);
        container = copy;
        size++;
    }

    private void removeElement(int index) {
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        size--;
    }

    private boolean elementsAreEqual(T el1, T el2) {
        if (el1 == el2) {
            return true;
        }
        if (el1 == null || el2 == null) {
            return false;
        }
        return el1.equals(el2);
    }
}
