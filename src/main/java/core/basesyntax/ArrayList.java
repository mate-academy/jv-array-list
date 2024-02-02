package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND_INDEX = -1;
    private static final int EMPTY_LIST_SIZE = 0;
    private static final int CHANGE_BY_ONE = 1;
    private static final int ARRAY_BEGINNING_INDEX = 0;
    private static final int DEFAULT_ARRAY_GROW_COEFFICIENT = 2;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= elements.length) {
            growArrayIfFull();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || checkForNegativeIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cannot add the value by index: "
                    + "Index is out of bounds");
        }
        if (size + CHANGE_BY_ONE > elements.length) {
            growArrayIfFull();
        }
        if (index == size) {
            elements[index] = value;
        }
        int numberOfElementsAfterIndex = size - index;
        System.arraycopy(elements, index,
                        elements, index + CHANGE_BY_ONE, numberOfElementsAfterIndex);
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
        checkIndexForException(index, "Cannot get the value by index: "
                + "Index is out of bounds");
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForException(index, "Cannot set value by index: "
                + "Index is out of bounds");
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForException(index, "Cannot remove the element by index: "
                + "Index is out of bounds");
        T removedElement = (T) elements[index];
        int startCopyIndex = index + CHANGE_BY_ONE;
        System.arraycopy(elements, startCopyIndex,
                        elements, index, size - startCopyIndex);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = ELEMENT_NOT_FOUND_INDEX;
        index = findElement(element);
        if (index != ELEMENT_NOT_FOUND_INDEX) {
            return remove(index);
        }
        throw new NoSuchElementException("Element is not found in the List");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY_LIST_SIZE;
    }

    private void growArrayIfFull() {
        T[] enlargedElements = (T[]) new Object[elements.length
                            + elements.length / DEFAULT_ARRAY_GROW_COEFFICIENT];
        System.arraycopy(elements, ARRAY_BEGINNING_INDEX,
                        enlargedElements, ARRAY_BEGINNING_INDEX, elements.length);
        elements = enlargedElements;
    }

    private void checkIndexForException(int index, String message) {
        if (checkIndexGreaterEqualSize(index) || checkForNegativeIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cannot get the value by index: "
                    + "Index is out of bounds");
        }
    }

    private boolean checkIndexGreaterEqualSize(int index) {
        return index >= size;
    }

    private boolean checkForNegativeIndex(int index) {
        return index < ARRAY_BEGINNING_INDEX;
    }

    private int findElement(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null && elements[i].equals(element)
                    || elements[i] == element) {
                return i;
            }
        }
        return ELEMENT_NOT_FOUND_INDEX;
    }
}
