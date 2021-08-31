package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String MESSAGE = "Error! Index must be greater then 0 and less then ";
    private static final int SIZE_BY_DEFAULT = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[SIZE_BY_DEFAULT];
    }

    @Override
    public void add(T value) {
        checkSize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
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
        checkIndex(index + 1);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index + 1);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index + 1);
        T element = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == element) || (element != null && element.equals(elements[i]))) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Error! No such element was found!");
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
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE + size);
        }
    }

    private void checkSize() {
        if (size == elements.length) {
            resizeArray();
        }
    }

    private void resizeArray() {
        T[] newArray = (T[]) new Object[size + size / 2];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }
}
