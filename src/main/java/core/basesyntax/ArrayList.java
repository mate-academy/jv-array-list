package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private static final double GROW_FACTOR = 1.5;

    private Element<T>[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Element[INITIAL_SIZE];
    }

    @Override
    public void add(T value) {
        ensureSize(size++);
        elements[size - 1] = new Element<>(value);
    }

    @Override
    public void add(T value, int index) {
        validateIndex(index, ++size);
        ensureSize(size);
        if (index + 1 < size) {
            System.arraycopy(elements, index, elements, index + 1, size - index - 1);
        }
        elements[index] = new Element<>(value);
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("Input list is null");
        }
        if (list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return elements[index].getValue();
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        elements[index] = new Element<>(value);
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        final T value = elements[index].getValue();
        size--;
        if (index < size) {
            System.arraycopy(elements, index + 1, elements, index, size - index);
        }
        elements[size] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        Element<T> holder = new Element<>(element);
        for (int i = 0; i < size; i++) {
            if ((elements[i] == holder)
                    || (elements[i] != null && elements[i].equals(holder))) {
                remove(i);
                return holder.getValue();
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
            Element<T>[] newArray = new Element[(int) (elements.length * GROW_FACTOR)];
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
