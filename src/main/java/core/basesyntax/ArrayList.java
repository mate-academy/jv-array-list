package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int NUMBER_TO_MULTIPLY = 3;
    private static final int NUMBER_TO_DIVIDE = 2;
    private static final int NUMBER_TO_SUBTRACT = 1;
    private static final int NUMBER_TO_PLUS = 1;
    private static final int ZERO_NUMBER = 0;
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Index out of bounds: ";
    private static final String ELEMENT_NOT_FOUND_MESSAGE = "Element not found: ";
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + NUMBER_TO_PLUS);
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE + index);
        }
        ensureCapacity(size + NUMBER_TO_PLUS);
        System.arraycopy(elements, index, elements, index + NUMBER_TO_PLUS, size - index);
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
        getValidIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        getValidIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        getValidIndex(index);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index
                + NUMBER_TO_PLUS, elements, index, size - index - NUMBER_TO_SUBTRACT);
        elements[--size] = null;
        return removedElement;
    }

    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? elements[i] == null : element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(ELEMENT_NOT_FOUND_MESSAGE + element);
    }

    public void getValidIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE + index);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == ZERO_NUMBER;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length * NUMBER_TO_MULTIPLY
                    / NUMBER_TO_DIVIDE + NUMBER_TO_PLUS;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elements, ZERO_NUMBER, newArray, ZERO_NUMBER, size);
            elements = newArray;
        }
    }
}
