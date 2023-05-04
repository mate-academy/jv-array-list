package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            elements = grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || size < index) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist: " + index);
        }
        if (size == elements.length) {
            elements = grow();
        }
        System.arraycopy(elements, index, elements, index + 1, elements.length - index - 1);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
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
        T deletedElement = elements[index];
        size--;
        System.arraycopy(elements, index + 1, elements, index, size - index);
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        return remove(getIndex(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        int newCapacity = elements.length + (elements.length >> 1);
        T[] newElements = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        return newElements;
    }

    private int getIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                return i;
            }
        }
        throw new NoSuchElementException("There is no such element as " + element);
    }

    private void checkIndex(int index) {
        if (size < index + 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist: " + index);
        }
    }
}
