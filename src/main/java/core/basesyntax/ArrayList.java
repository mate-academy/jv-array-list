package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elements;

    public ArrayList() {
        size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayIsFull();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, index > size());
        growIfArrayIsFull();
        Object[] elements = this.elements;
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        growForAddList(list.size());
        for (int i = 0; i < list.size(); i++) {
            elements[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkIndex(index, index >= size());
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, index >= size());
        growIfArrayIsFull();
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, index >= size());
        Object[] newElements = new Object[size - 1];
        System.arraycopy(elements, 0, newElements, 0, index);
        System.arraycopy(elements, index + 1, newElements, index, newElements.length - index);
        T removedItem = (T) elements[index];
        elements = newElements;
        size--;
        return removedItem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                if (elements[i] == element) {
                    remove(i);
                    return element;
                } else {
                    continue;
                }
            } else if (elements[i].equals(element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Element " + element + "doesn't exist in ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayIsFull() {
        if (size == elements.length) {
            int newCapacity = size + (size >> 1);
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }

    private void growForAddList(int listSize) {
        if (listSize >= elements.length) {
            int newCapacity = size + listSize + ((size + listSize) >> 1);
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }

    private void checkIndex(int index, boolean outOfBoundCondition) {
        if (index < 0 || outOfBoundCondition) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index " + index
                    + " out of bounds." + "Select an index by ArrayList length");
        }
    }
}
