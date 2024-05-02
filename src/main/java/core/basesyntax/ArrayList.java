package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
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
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index.");
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
        verifyIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        verifyIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        verifyIndex(index);
        final T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        T removedElement = (T) new Object();
        for (int i = 0; i < size; i++) {
            if ((elements[i] != null && elements[i].equals(element))
                    || elements[i] == null && element == null) {
                removedElement = elements[i];
                remove(i);
                return removedElement;
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
        if (size >= elements.length) {
            int newSize = (int) (size * SIZE_MULTIPLIER);
            T[] tempElements = (T[]) new Object[newSize];
            System.arraycopy(elements, 0, tempElements, 0, elements.length);
            elements = tempElements;
        }
    }

    public void verifyIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index.");
        }
    }
}
