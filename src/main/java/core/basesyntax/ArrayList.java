package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROW_FACTOR = 2;
    private T[] elements;
    private int size;
    private int capacity;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        T[] clonedArray = (T[]) super.clone();
        System.arraycopy(elements, 0, clonedArray, 0, size);
        return clonedArray;
    }

    @Override
    public void add(T value) {
        resize();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index
                    + "; array size: " + size);
        }
        resize();
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
        T arrayElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return arrayElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null) {
                if (element == null) {
                    return remove(i);
                }
            } else {
                if (elements[i].equals(element)) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("The element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index
                    + "; array size: " + size);
        }
    }

    private void resize() {
        if (size == capacity) {
            T[] initialArray = elements;
            elements = (T[]) new Object[capacity + capacity / GROW_FACTOR];
            System.arraycopy(initialArray, 0, elements, 0, size);
            capacity = elements.length;
        }
    }
}
