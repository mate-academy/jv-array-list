package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            resize();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        size++;
        checkIndex(index);
        if (size == elements.length) {
            resize();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
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
    public T remove(int index) {
        checkIndex(index);
        T removeEl = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removeEl;
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        T removedEl = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
        return removedEl;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void resize() {
        Object[] copyArray = new Object[elements.length + (elements.length >> 1)];
        System.arraycopy(elements, 0, copyArray, 0, size);
        elements = copyArray;
    }

    private void checkIndex(int index) {
        if (index >= size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid");
        }
    }

    private int getIndex(T value) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == value || elements[i] != null && elements[i].equals(value)) {
                return i;
            }
        }
        throw new NoSuchElementException("No such  " + value + " element");
    }
}
