package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist.");
        }
        if (size == elements.length) {
            grow();
        }
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] newArray = new Object[size + list.size()];
        System.arraycopy(elements, 0, newArray, 0, size);
        for (int i = 0; i < list.size(); i++) {
            newArray[size + i] = list.get(i);
        }
        elements = newArray;
        size += list.size();
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        final Object removedObject = elements[index];
        Object[] newArray = new Object[size - 1];
        System.arraycopy(elements, 0, newArray, 0, index);
        System.arraycopy(elements, index + 1, newArray, index, size - index - 1);
        elements = newArray;
        size--;
        return (T) removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? elements[i] == null : element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found in the list.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void grow() {
        Object[] newArray = new Object[elements.length + elements.length / 2];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist.");
        }
    }
}
