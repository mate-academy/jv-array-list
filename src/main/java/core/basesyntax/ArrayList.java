package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double SIZE_MULTIPLIER = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("There is no such index");
        }
        grow();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = size + list.size();
        ensureCapacity(newSize);
        for (int i = 0; i < list.size(); i++) {
            elements[size + i] = list.get(i);
        }
        size = newSize;
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
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == null && element == null)
                    || (elements[i] != null && elements[i].equals(element))) {
                T removed = elements[i];
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                size--;
                return removed;
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

    private void grow() {
        if (size >= elements.length) {
            int newSize = (int) (elements.length * SIZE_MULTIPLIER);
            if (newSize < size + 1) {
                newSize = size + 1;
            }
            ensureCapacity(newSize);
        }
    }

    private void ensureCapacity(int newSize) {
        if (newSize >= elements.length) {
            T[] newArray = (T[]) new Object[newSize];
            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
