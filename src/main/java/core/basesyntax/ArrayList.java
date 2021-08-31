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
    public void add(T object) {
        checkSize();
        elements[size] = object;
        size++;
    }

    @Override
    public void add(T object, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE + size);
        }
        checkSize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = object;
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
    public void set(T object, int index) {
        checkIndex(index);
        elements[index] = object;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedObject = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedObject;
    }

    @Override
    public T remove(T object) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == object) || (object != null && object.equals(elements[i]))) {
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
            resizeArray();
        }
    }

    private void resizeArray() {
        T[] newArray = (T[]) new Object[size + size / 2];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }
}
