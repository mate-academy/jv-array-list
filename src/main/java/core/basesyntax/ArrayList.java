package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;

    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfFool();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is not found. "
                    + "Index must be less than " + size() + "!");
        }
        growIfFool();
        if (index < size) {
            System.arraycopy(elements, index, elements, index + 1, size() - index);
        }
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
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size() - index - 1);
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (element == elements[i] || element != null && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " is not found!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfFool() {
        if (size() == elements.length) {
            int newCapacity = (int) (elements.length * GROW_FACTOR);
            T[] newElements = (T[]) new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size());
            elements = newElements;
        }
    }

    private Exception checkIndex(int index) {
        if (index >= size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is not found. "
                    + "Index must be less or equals than " + size() + "!");
        }
        return null;
    }

}
