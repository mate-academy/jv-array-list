package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_STEP = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {

        checkSize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        checkSize();
        System.arraycopy(elements, index,
                elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {

        for (int i = 0; i < list.size(); i++) {
            checkSize();
            elements[size++] = list.get(i);
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
    public T remove(int index) {
        checkIndex(index);
        size--;
        Object element = elements[index];
        if (size > index) {
            System.arraycopy(elements, index + 1, elements,
                    index, size - index);
        }
        elements[size] = null;
        return (T) element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elements[i] == null)
                    || element != null && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkSize() {
        if (size == elements.length) {
            grow();
        }
    }

    private void grow() {
        int newCapacity = (int) (elements.length * GROW_STEP);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0,
                newArray, 0, size);
        elements = (T[]) newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid Index: " + index);
        }
    }
}
