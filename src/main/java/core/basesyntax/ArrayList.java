package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        elements = new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    private void resizeCapacity() {
        if (size == elements.length) {
            Object[] buffer = new Object[size + (size >> 1)];
            System.arraycopy(elements, 0, buffer, 0, size);
            elements = buffer;
        }
    }

    @Override
    public void add(T value) {
        resizeCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        resizeCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            while (elements.length - size < list.size()) {
                resizeCapacity();
            }
            for (int i = 0; i < list.size(); i++) {
                elements[size] = list.get(i);
                size++;
            }
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        T element = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
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
