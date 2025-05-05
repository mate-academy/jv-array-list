package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ifFullResize();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        ifFullResize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
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
        validateIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(element)
                    || elements[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ifFullResize() {
        if (size == elements.length) {
            resize();
        }
    }

    private void resize() {
        int newCapacity = elements.length * (elements.length / 2);
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index
                    + " is out of bounds for size "
                    + size);
        }
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index
                    + " is out of bounds for size "
                    + size);
        }
    }
}
