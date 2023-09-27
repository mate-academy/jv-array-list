package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASING_FACTOR = 1.5;
    private int size = 0;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size() == elements.length) {
            grow();
        }
        elements[size()] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException("index " + index
                    + " is out of bounds. Size =  " + size());
        }
        if (size() == elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size() - index);
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
        if (size() == elements.length) {
            grow();
        }
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size() - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int elementPosition = -1;
        for (int i = 0; i < size(); i++) {
            if (element == elements[i] || element != null && element.equals(elements[i])) {
                elementPosition = i;
            }
        }
        if (elementPosition == -1) {
            throw new NoSuchElementException("element was not found");
        }
        T removedElement = elements[elementPosition];
        System.arraycopy(elements, elementPosition + 1, elements, elementPosition,
                size() - elementPosition);
        size--;
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void grow() {
        T[] newElements = (T[]) new Object[(int) (elements.length * INCREASING_FACTOR)];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("index " + index
                    + " is out of bounds. Size =  " + size());
        }
    }
}
