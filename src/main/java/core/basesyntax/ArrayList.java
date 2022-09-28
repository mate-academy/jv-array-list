package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        elements = new Object[initCapacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(T value) {
        grow();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == elements.length) {
            grow();
        }
        if (index >= 0 && index <= size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid.");
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() >= size) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            elements[size] = list.get(i);
            size++;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        isInvalidIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        isInvalidIndex(index);
        elements[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        isInvalidIndex(index);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;

        return removedElement;
    }

    @Override
    public T remove(T element) {
        T removedElement = null;
        for (int i = 0; i < elements.length; i++) {
            if (element == elements[i] || element != null && element.equals(elements[i])) {
                removedElement = remove(i);
                return removedElement;
            }
        }
        throw new NoSuchElementException();
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

        if (elements.length == size) {
            Object[] newArray = new Object[elements.length + elements.length / 2];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void isInvalidIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }

    }
}
