package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_SIZE = 1.5;
    private static final int FIRST_POSITION_ARRAY = 0;
    private T[] elements;
    private int size;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizedIfNeeded();
        elements[size] = value;
        size++;

    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index to add: " + index);
        }
        resizedIfNeeded();
        System.arraycopy(
                elements, index,
                elements, index + 1,
                size - index);
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
            throw new ArrayListIndexOutOfBoundsException("Invalid index to get: " + index);
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index to set: " + index);
        }
        elements[index] = value;

    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index for remove: " + index);
        }
        T removedElement = get(index);
        System.arraycopy(
                elements, index + 1,
                elements, index,
                size - index - 1);
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null) {
                if (element == null) {
                    return remove(i);
                }
            } else if (elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizedIfNeeded() {
        if (elements.length == size) {
            Object[] newArray = new Object[(int) (elements.length * GROWTH_SIZE)];
            System.arraycopy(
                    elements, FIRST_POSITION_ARRAY,
                    newArray, FIRST_POSITION_ARRAY,
                    size);
            elements = (T[]) newArray;
        }
    }
}
