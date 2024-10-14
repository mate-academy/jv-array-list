package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size = 0;

    public ArrayList() {

        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void add(T value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }

    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        ensureCapacity();
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        return elements[index];
    }

    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        elements[index] = value;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        final T removedElement = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        elements[size] = null;
        return removedElement;
    }

    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i] != null && elements[i].equals(element)) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            grow();
        }
    }

    private void grow() {
        int newCapacity = (int) (elements.length * 1.5);
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
        }
        elements = newArray;
    }

    public int size() {

        return size;
    }

    public boolean isEmpty() {

        return size == 0;
    }

    public void addAll(ArrayList<T> otherList) {
        for (int i = 0; i < otherList.size(); i++) {
            add(otherList.get(i)); // Reuse the existing add method to add elements
        }
    }
}
