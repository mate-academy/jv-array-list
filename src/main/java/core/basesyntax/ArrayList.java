package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int BIT_DIVIDER = 1;
    private static final int SOURCE_POSITION = 0;
    private static final int DESTINATION_POSITION = 0;
    private static final int SHIFT_INDEX_NUMBER = 1;
    private static final int SIZE_INITIALIZER = 0;
    private int size;
    private T[] elements;

    public ArrayList() {
        size = SIZE_INITIALIZER;
        elements = (T[]) new Object[DEFAULT_CAPACITY];
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
        System.arraycopy(elements, index, elements, index + SHIFT_INDEX_NUMBER, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        growForAddList(list.size());
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
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
        T removedItem = elements[index];
        System.arraycopy(elements, index + SHIFT_INDEX_NUMBER, elements, index,
                size - index - SHIFT_INDEX_NUMBER);
        size--;
        return removedItem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == null && element == null)
                    || (elements[i] != null && elements[i].equals(element))) {
                return remove(i);
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
            int newCapacity = size + (size >> BIT_DIVIDER);
            T[] newElements = (T[]) new Object[newCapacity];
            System.arraycopy(elements, SOURCE_POSITION, newElements,
                    DESTINATION_POSITION, elements.length);
            elements = newElements;
        }
    }

    private void growForAddList(int listSize) {
        if (listSize >= elements.length) {
            int newCapacity = size + listSize + ((size + listSize) >> BIT_DIVIDER);
            T[] newElements = (T[]) new Object[newCapacity];
            System.arraycopy(elements, SOURCE_POSITION, newElements,
                    DESTINATION_POSITION, elements.length);
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
