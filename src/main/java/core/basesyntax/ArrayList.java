package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 5;
    private Object[] elements;
    private int size;

    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        } else {
            elements = new Object[initCapacity];
        }
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(T value) {
        reSizeIdNeeded();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index >= size + 1) {
            throw new ArrayListIndexOutOfBoundsException("Can not added elements, index is wrong!");
        }
        reSizeIdNeeded();
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist!");
        }
        return (T)elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist!");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist!");
        }
        T removedElement = (T)elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) { //{1, 2, 3, null, null, null, null, null, null, null} size = 3;
        T removedElement;
        for (int index = 0; index < size; index++) {
            if (Objects.equals(elements[index], element)) {
                removedElement = (T)elements[index];
                System.arraycopy(elements, index + 1, elements, index, size - index - 1);
                size--;
                return removedElement;
            }
        }
        throw new NoSuchElementException("Element does not exist!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void reSizeIdNeeded() {
        if (elements.length == size) {
            Object[] newArray = new Object[size * 2];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }
}


