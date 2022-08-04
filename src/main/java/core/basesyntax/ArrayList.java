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
        if (isOutOfBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("Can't get element by index: " + index
                    + ", it's out of bounds");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (isOutOfBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("Can't set element by index: " + index
                    + ", it's out of bounds");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isOutOfBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("Can't remove element by index: " + index
                    + ", it's out of bounds");
        }
        Object deletedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return (T) deletedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null && element == null
                    || elements[i] != null && elements[i].equals(element)) {
                System.arraycopy(elements, i + 1, elements, i, size - i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("No such element " + element.toString() + " for remove");
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
        Object[] newElementData = new Object[oldCapacity + (oldCapacity >> 1)];
        System.arraycopy(elements, 0, newElementData, 0, oldCapacity);
        elements = newElementData;
    }

    private boolean isOutOfBounds(int index) {
        return (index < 0 || index >= size);
    }
}
