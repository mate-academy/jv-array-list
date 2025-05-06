package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void resizeArray(int minCapacity) {
        int newCapacity = elements.length + (elements.length / 2);
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            resizeArray(minCapacity);
        }
    }

    private void checkIndex(int index, boolean allowEnd) {
        if (index < 0 || index > size || (!allowEnd && index == size)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + index);
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index,true);

        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elements[i] = elements [i - 1];
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("Null list is not allowed");
        }
        int listSize = list.size();
        ensureCapacity(size + listSize);

        for (int i = 0; i < listSize; i++) {
            elements[size + i] = list.get(i);
        }
        size += listSize;
    }

    @Override
    public T get(int index) {
        checkIndex(index, false);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, false);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index,false);
        final T removedElement = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements [i + 1];
        }
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == element) || (elements[i] != null && elements[i].equals(element))) {
                return remove(i);
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
}
