package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
        }
        elements = new Object[initialCapacity];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        if (index < size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }
        elements[index] = value;
        size++;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= elements.length) {
            return;
        }
        int newCapacity = elements.length + (elements.length >> 1);
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void rangeCheckForAdd(int index) {
        if (0 > index || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void rangeCheck(int index) {
        if (0 > index || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list.isEmpty()) {
            return;
        }
        int newSize = size + list.size();
        ensureCapacity(newSize);
        for (int i = 0; i < list.size(); i++) {
            elements[size + i] = list.get(i);
        }
        size = newSize;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elements[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        rangeCheck(index);
        Object oldElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return (T) oldElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || (elements[i] != null && elements[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " not found in the list");
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
