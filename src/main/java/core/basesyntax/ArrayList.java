package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final double CAPACITY_MULTIPLIER = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkSize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index " + index + " of list");
        }
        checkSize();
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
        isIndexValid(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexValid(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexValid(index);
        T removedElement = elements[index];
        if (index + 1 != size) {
            System.arraycopy(elements, index + 1, elements, index, size - index);
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || (elements[i] != null && elements[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element " + element);
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

    private void isIndexValid(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index" + index + "of list");
        }
    }

    private void grow() {
        T[] copy = (T[]) new Object[(int)(elements.length * CAPACITY_MULTIPLIER)];
        System.arraycopy(elements, 0, copy, 0, size);
        elements = copy;
    }

}
