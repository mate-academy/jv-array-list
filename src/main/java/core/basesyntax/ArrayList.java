package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int ENSURE_MULTIPLIER = 2;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index!");
        }
        if (index == size) {
            add(value);
            return;
        }
        ensureCapacity();
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

    public void throwOutOfBoundException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Element with index "
                + index + " does not exists!");
        }
    }

    @Override
    public T get(int index) {
        throwOutOfBoundException(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        throwOutOfBoundException(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        throwOutOfBoundException(index);
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size * ENSURE_MULTIPLIER);
        }
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (equals(elements[i], element)) {
                return i;
            }
        }
        throw new NoSuchElementException("Element not found in the list.");
    }

    public boolean equals(Object element1, Object element2) {
        if (element1 == element2) {
            return true;
        }
        if (element1 == null || element2 == null) {
            return false;
        }
        return element1.equals(element2);
    }
}
