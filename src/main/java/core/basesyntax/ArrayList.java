package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elements;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index
                    + " or less than 0 or greater than " + size);
        }
        if (size == index) {
            add(value);
        } else {
            resize();
            for (int i = size; i > index; i--) {
                elements[i] = elements[i - 1];
            }
            elements[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        errorChecking(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        errorChecking(index);
        if (index == size) {
            add(value);
        } else {
            elements[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        errorChecking(index);
        T[] beginning = (T[]) new Object[index];
        T[] end = (T[]) new Object[elements.length - index - 1];
        System.arraycopy(elements, 0, beginning, 0, index);
        System.arraycopy(elements, index + 1, end, 0, elements.length - index - 1);
        T result = elements[index];
        for (int i = 0; i < size - 1; i++) {
            if (i < index) {
                elements[i] = beginning[i];
            } else {
                elements[i] = end[i - index];
            }
        }
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(element) || elements[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(elements + " does not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (size == elements.length) {
            T[] arr = elements;
            elements = (T[]) new Object[(int) (size * 1.5)];
            for (int i = 0; i < arr.length; i++) {
                elements[i] = arr[i];
            }
        }
    }

    private void errorChecking(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index
                    + " or less than 0 or greater than " + size);
        }
    }
}
