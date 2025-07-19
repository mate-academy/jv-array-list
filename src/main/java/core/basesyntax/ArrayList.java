package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {

        if (size == elements.length) {
            T[] newArrays = (T[]) new Object[elements.length + (elements.length / 2)];
            System.arraycopy(elements, 0, newArrays, 0, elements.length);
            elements = newArrays;
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {

        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }

        if (size == elements.length) {
            T[] newArrays = (T[]) new Object[elements.length + (elements.length / 2)];
            System.arraycopy(elements, 0, newArrays, 0, elements.length);
            elements = newArrays;
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {

        if (elements.length < size + list.size()) {
            T[] newArrays = (T[]) new Object[size + list.size() + (elements.length / 2)];
            System.arraycopy(elements, 0, newArrays, 0, size);
            elements = newArrays;
        }

        for (int i = 0; i < list.size(); i++) {
            elements[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {

        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {

        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {

        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }

        final T t = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        return t;
    }

    @Override
    public T remove(T element) {

        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elements[i] == null) {
                    return remove(i);
                }
            } else {
                if (elements[i] != null && elements[i].equals(element)) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("Error not found element.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
