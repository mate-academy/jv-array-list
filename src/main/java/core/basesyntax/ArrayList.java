package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public ArrayList(int capacity) {
        elements = (T[]) new Object[capacity];
        size = 0;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null ? elements[i] == null : o.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException();
        }
        if (size == elements.length) {
            grow();
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements [i - 1];
        }
        elements[index] = value;
        size++;
    }

    @Override
    public boolean add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = value;
        size++;
        return true;

    }

    private void grow() {
        Object[] newElements = new Object[elements.length + elements.length / 2];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = (T[]) newElements;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            add(element);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException();
        }
        return (T) elements[index];
    }

    @Override
    public T set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException();
        }
        T replace = (T) elements[index];
        elements[index] = value;
        return replace;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException();
        }
        final T removedElement = (T) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || (elements[i] != null && elements[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element " + element);
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
