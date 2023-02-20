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
        elements[size()] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index of list");
        }
        checkSize();
        for (int j = size(); j > index; j--) {
            elements[j] = elements[j - 1];
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            checkSize();
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexValid(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexValid(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValid(index);
        T removedElement = elements[index];
        for (int i = index; i + 1 < size(); i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (element == elements[i] || (elements[i] != null && elements[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element exeption");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    private void checkSize() {
        if (size == elements.length) {
            T[] copy = (T[]) new Object[(int)(elements.length * CAPACITY_MULTIPLIER)];
            System.arraycopy(elements, 0, copy, 0, size);
            elements = copy;
        }
    }

    private void indexValid(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index of list");
        }
    }

}
