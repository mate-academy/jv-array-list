package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private static final String MESSAGE = "Error! Index must be greater then 0 and less then ";
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[INITIAL_SIZE];
    }

    @Override
    public void add(T element) {
        checkSize();
        elements[size] = element;
        size++;
    }

    @Override
    public void add(T element, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE + size);
        }
        checkSize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
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
    public void set(T element, int index) {
        checkIndex(index);
        elements[index] = element;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == element) || (element != null && element.equals(elements[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Error! Input object wasn't found!");
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE + size);
        }
    }

    private void checkSize() {
        if (size == elements.length) {
            grow();
        }
    }

    private void grow() {
        T[] newArray = (T[]) new Object[size + size / 2];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }
}
