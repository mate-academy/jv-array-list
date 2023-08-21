package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfFull();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (checkAddIndex(index)) {
            growIfFull();
            elements = addResize(index);
            elements[index] = value;
            size++;
        }
    }

    private Object[] addResize(int index) {
        System.arraycopy(elements, index, elements, index + 1, size - index);
        return elements;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;

    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || element != null && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such an element " + element);
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removeValue = (T) elements[index];
        elements = removeResize(index);
        elements[--size] = null;
        return removeValue;
    }

    private Object[] removeResize(int index) {
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        return elements;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    private void growIfFull() {
        if (isFull()) {
            int newCapacity = size + (size >> 1);
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    private boolean checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }
        return true;
    }

    private boolean checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }
        return true;
    }

    private boolean isFull() {
        return size >= elements.length;
    }
}
