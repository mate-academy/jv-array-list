package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int INITIAL_CAPACITY = 10;
    private static final double RESIZE_FACTOR = 1.5;
    private int size;
    private T[] elements = (T[]) new Object[INITIAL_CAPACITY];

    @Override
    public void add(T value) {
        if (size == INITIAL_CAPACITY) {
            changeSize();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index value");
        }
        if (size == INITIAL_CAPACITY) {
            changeSize();
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (size == INITIAL_CAPACITY) {
                changeSize();
            }
            elements[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't get element from invalid index");
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't set element with invalid index");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Can't remove element with index %s", index));
        }
        final T removeElement = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            size--;
            return null;
        }
        T removeElement = null;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null && elements[i].equals(element)) {
                removeElement = remove(i);
            }
        }
        if (removeElement == null) {
            throw new NoSuchElementException("Can't find element to remove ");
        } else {
            return removeElement;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void changeSize() {
        INITIAL_CAPACITY *= RESIZE_FACTOR;
        T[] tmp = elements;
        elements = (T[]) new Object[INITIAL_CAPACITY];
        for (int i = 0; i < size; i++) {
            elements[i] = tmp[i];
        }
    }
}
