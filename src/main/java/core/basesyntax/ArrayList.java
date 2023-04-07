package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAddElement(index);
        if ((size + 1) > elements.length) {
            grow(size);
        }
        System.arraycopy(elements, index, elements, index + 1,
                size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if ((size + list.size()) > elements.length) {
            grow(size + list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexIsValid(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexIsValid(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexIsValid(index);
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index,
                size - 1 - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < this.elements.length; i++) {
            if ((this.elements[i] == element)
                    || (this.elements[i] != null && this.elements[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't remove non existent value " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow(int oldSize) {
        int newSize = (oldSize + oldSize / 2);
        T[] newElementData = (T[]) new Object[newSize];
        System.arraycopy(elements, 0, newElementData, 0, elements.length);
        elements = newElementData;
    }

    private void checkIndexIsValid(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element on position " + index);
        }
    }

    private void checkIndexForAddElement(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element on position " + index);
        }
    }
}
