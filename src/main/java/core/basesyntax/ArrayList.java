package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MULTIPLIER = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            increaseArrayIfFull();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        throwIfIndexOutOfBoundsForAdd(index);

        if (size == elements.length) {
            increaseArrayIfFull();
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.isEmpty()) {
            return;
        }

        if (list.size() + size > elements.length) {
            increaseArrayIfFull();
        }

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        throwIfIndexOutOfBounds(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        throwIfIndexOutOfBounds(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        throwIfIndexOutOfBounds(index);

        final T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i]
                    || element != null && element.equals(elements[i])) {
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

    @SuppressWarnings("unchecked")
    private void increaseArrayIfFull() {
        if (size > 9) {
            int newSizeOfArray = (int) (elements.length * MULTIPLIER);
            T[] newArray = (T[]) new Object[newSizeOfArray];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void throwIfIndexOutOfBoundsForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

    private void throwIfIndexOutOfBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }
}
