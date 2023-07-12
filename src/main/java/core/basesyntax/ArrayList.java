package core.basesyntax;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[])new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initCapacity) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException(
                    "Can't create array with negative length");
        }
        elements = (T[])new Object[initCapacity];
    }

    @Override
    public void add(T value) {
        growIfFuel();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndexOutOfBoundException(index);
        }
        growIfFuel();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (T element: list) {
            add(element);
        }
    }

    @Override
    public T get(int index) {
        checkIndexOutOfBoundException(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexOutOfBoundException(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexOutOfBoundException(index);
        T removedElement = elements[index];
        fastRemoveElement(index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int indexRemovedElement = getElementIndex(element);
        T removedElement = elements[getElementIndex(element)];
        fastRemoveElement(indexRemovedElement);
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return (index < elements.length && elements[index] != null);
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[index++];
            }
        };
    }

    private void growIfFuel() {
        if (elements.length == 1 && elements.length == size) {
            T[] newArray = (T[])new Object[DEFAULT_CAPACITY];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
        if (elements.length == size) {
            int newCapacity = (int)(elements.length * GROWTH_FACTOR);
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void checkIndexOutOfBoundException(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + " out of array size: " + size);
        }
    }

    private int getElementIndex(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i] != null
                    && elements[i].equals(element)
                    || elements[i] == element) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Can't find element: " + element);
        }
        return index;
    }

    private void fastRemoveElement(int index) {
        if (index == elements.length - 1) {
            elements[size - 1] = null;
        } else {
            System.arraycopy(elements,index + 1, elements, index, size - index - 1);
        }
        size--;
    }
}
