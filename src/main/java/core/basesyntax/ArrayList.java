package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= elements.length) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element by index: " + index
                    + ", it's out of bounds");
        }
        if (size == elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        this.elements[index] = value;
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
        checkOutOfBounds(index, "get");
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkOutOfBounds(index, "set");
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkOutOfBounds(index, "remove");
        T deletedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element
                    || elements[i] != null && elements[i].equals(element)) {
                System.arraycopy(elements, i + 1, elements, i, size - i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("No such element " + element + " in List");
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
        int oldCapacity = elements.length;
        T[] newElementData = (T[]) new Object[oldCapacity + (oldCapacity >> 1)];
        System.arraycopy(elements, 0, newElementData, 0, oldCapacity);
        elements = newElementData;
    }

    private void checkOutOfBounds(int index, String actionString) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't " + actionString
                    + " element by index: " + index
                    + ", it's out of bounds");
        }
    }
}
