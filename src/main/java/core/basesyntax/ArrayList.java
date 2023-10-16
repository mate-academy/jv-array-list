package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elements;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (elements.length == size) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIfCanAddByIndex(index);

        if (elements.length == size) {
            grow();
        }

        System.arraycopy(elements, index, elements, index + 1, elements.length - index - 1);
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
        checkIfIndexExists(index);

        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexExists(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexExists(index);
        T element = elements[index];
        System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
        size--;

        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            boolean areValuesEqual = element == elements[i]
                    || element != null && element.equals(elements[i]);

            if (areValuesEqual) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("Can't remove " + element + " from the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int newCapacity = elements.length + elements.length / 2;
        T[] newElementsArray = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElementsArray, 0, elements.length);
        elements = newElementsArray;
    }

    private void checkIfCanAddByIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Failed to add - index \"" + index
                    + "\" is out of bounds"
            );
        }
    }

    private void checkIfIndexExists(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index \"" + index
                    + "\" is out of bounds"
            );
        }
    }
}
