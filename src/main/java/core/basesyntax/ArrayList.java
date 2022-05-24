package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAYLIST_CAPACITY = 10;
    private int size = 0;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_ARRAYLIST_CAPACITY];
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
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        grow();
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
        if (index != size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - 1);
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && elements[i] == null || elements[i] != null
                    && elements[i].equals(element)) {
                return remove(i);
            }

        }
        throw new NoSuchElementException("No such element in list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }

    }

    public void grow() {
        if (size == elements.length) {
            int oldCapacity = elements.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            Object[] enlargedElements = new Object[newCapacity];
            System.arraycopy(elements, 0, enlargedElements, 0, elements.length);
            elements = (T[]) enlargedElements;
        }
    }
}
