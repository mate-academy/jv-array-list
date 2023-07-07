package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;

    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[INITIAL_SIZE];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        validateIndex(index, size + 1);
        ensureSize(size + 1);
        if (index < size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        validateIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        elements[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        validateIndex(index);
        final T value = (T) elements[index];
        size--;
        if (index < size) {
            System.arraycopy(elements, index + 1, elements, index, size - index);
        }
        elements[size] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == element)
                    || (elements[i] != null && elements[i].equals(element))) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element in the list: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureSize(int size) {
        if (size >= elements.length) {
            Object[] newArray = new Object[elements.length * 3 / 2];
            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
        }
    }

    private void validateIndex(int index) {
        validateIndex(index, size);
    }

    private void validateIndex(int index, int upperBound) {
        if (index >= upperBound) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid: "
                    + index + "; index greater than array size; array size: " + size);
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid: "
                    + index + "; index should be positive; array size: " + size);
        }
    }
}
