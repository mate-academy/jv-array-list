package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int MAX_START_LENGTH = 10;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[MAX_START_LENGTH];
    }

    private void grow() {
        T[] newArray = (T[]) new Object[elements.length + elements.length / 2];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void exception(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index  " + index + " is not "
                    + "correct for size" + size);
        }
    }

    @Override
    public void add(T value) {
        if (elements.length == size) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not correct ");
        }
        if (elements.length == size) {
            grow();
        }
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Default index");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        exception(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        exception(index);
        T oldObjects = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return oldObjects;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] != null && elements[i].equals(element))
                    || elements[i] == element) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Is not correct element ");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
