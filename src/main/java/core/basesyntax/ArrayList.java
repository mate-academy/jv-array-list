package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        elements = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        grow();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("There is no such index");
        }
        grow();
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            grow();
            elements[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("There is no such index");
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("There is no such index");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("There is no such index");
        }
        T removedElement = elements[index];
        for (int i = index; i < size; i++) {
            grow();
            elements[i] = elements[i + 1];
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        boolean isExecuted = false;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(element)) {
                index = i;
                isExecuted = true;
            }
            if (elements[i] == null && element == null) {
                index = i;
                isExecuted = true;
            }
        }
        if (!isExecuted) {
            throw new NoSuchElementException("There is no such element");
        }
        T removedElement = elements[index];
        for (int i = index; i < size; i++) {
            grow();
            elements[i] = elements[i + 1];
        }
        size--;
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

    public void grow() {
        if (size >= elements.length) {
            int newSize = (size * 3) / 2;
            T[] copyElements = (T[]) new Object[newSize];
            System.arraycopy(elements, 0, copyElements, 0, elements.length);
            elements = copyElements;
        }
    }
}
