package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ZERO = 0;
    private static final int AUXILIARY_ONE = 1;
    private static final int DEFAULT_SIZE = 10;
    private static final double MULTIPLICATION = 1.5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_SIZE];
        size = ZERO;
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * MULTIPLICATION);
            elements = Arrays.copyOf(elements, newCapacity);
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < ZERO || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * MULTIPLICATION);
            elements = Arrays.copyOf(elements, newCapacity);
        }
        System.arraycopy(elements, index, elements, index + AUXILIARY_ONE, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = size + list.size();
        if (newSize > elements.length) {
            int newCapacity = (int) (elements.length * MULTIPLICATION);
            if (newCapacity < newSize) {
                newCapacity = newSize;
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }
        for (int i = ZERO; list.size() > i; i++) {
            elements[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        throwException(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        throwException(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        throwException(index);
        T removedElement = (T) elements[index];
        int numMove = size - index - AUXILIARY_ONE;
        if (numMove > ZERO) {
            System.arraycopy(elements, index + AUXILIARY_ONE, elements, index, numMove);
        }
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = ZERO; i < size; i++) {
            if (element == null ? elements[i] == null : element.equals(elements[i])) {
                T removedElement = (T) elements[i];
                int numMoved = size - i - AUXILIARY_ONE;
                if (numMoved > ZERO) {
                    System.arraycopy(elements, i + AUXILIARY_ONE, elements, i, numMoved);
                }
                elements[--size] = null;
                return removedElement;
            }
        }
        throw new NoSuchElementException("Element not found in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == ZERO;
    }

    private void throwException(int index) {
        if (index < ZERO || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }
}
