package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String INDEX_IS_BELOW_ZERO = "Index - %d is below zero";
    private static final String NO_SUCH_ELEMENT = "In ArrayListThere is no such element - %s";
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_COEFFICIENT = 1.5;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayIsFull();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIfIndexExist(index);
        growIfArrayIsFull();
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
        checkIfIndexExist(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexExist(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexExist(index);
        final T removed = elements[index];
        elements[index] = null;
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT, element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayIsFull() {
        if (elements.length == size) {
            T[] grownElements = (T[]) new Object[(int) (elements.length * GROW_COEFFICIENT)];
            System.arraycopy(elements, 0, grownElements, 0, elements.length);
            elements = grownElements;
        }
    }

    private void checkIfIndexExist(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(String.format(INDEX_IS_BELOW_ZERO, index));
        }
    }
}
