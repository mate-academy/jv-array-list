package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND_INDEX = -1;
    private static final int EMPTY_LIST_SIZE = 0;
    private static final int CHANGE_BY_ONE = 1;
    private static final int ARRAY_BEGINNING_INDEX = 0;
    private static final int DEFAULT_ARRAY_GROW_COEFFICIENT = 2;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= elements.length) {
            defaultGrowArrayIfFull();
        }
        elements[size] = value;
        increaseSize(CHANGE_BY_ONE);
    }

    @Override
    public void add(T value, int index) {
        if (index > size || checkForNegativeIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cannot add the value by index: "
                    + "Index is out of bounds");
        }
        if (size + CHANGE_BY_ONE > elements.length) {
            defaultGrowArrayIfFull();
        }
        if (index == size) {
            elements[index] = value;
        }
        int numberOfElementsAfterIndex = size - index;
        copyElements(elements, index, elements, index + CHANGE_BY_ONE, numberOfElementsAfterIndex);
        elements[index] = value;
        increaseSize(CHANGE_BY_ONE);
    }

    @Override
    public void addAll(List<T> list) {
        int resultingListSize = size + list.size();
        if (resultingListSize > elements.length) {
            givenSizeGrowArrayIfFull(resultingListSize);
        }
        for (int i = 0; i < list.size(); i++) {
            elements[size + i] = list.get(i);
        }
        increaseSize(list.size());
    }

    @Override
    public T get(int index) {
        if (checkIndexGreaterEqualSize(index) || checkForNegativeIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cannot get the value by index: "
                    + "Index is out of bounds");
        } else {
            return (T) elements[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (checkIndexGreaterEqualSize(index) || checkForNegativeIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cannot set value by index: "
                    + "Index is out of bounds");
        } else {
            elements[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (checkIndexGreaterEqualSize(index) || checkForNegativeIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cannot remove the element by index: "
                    + "Index is out of bounds");
        }
        T removedElement = (T) elements[index];
        int startCopyIndex = index + CHANGE_BY_ONE;
        copyElements(elements, startCopyIndex,
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

    private void defaultGrowArrayIfFull() {
        grow(elements.length + elements.length / DEFAULT_ARRAY_GROW_COEFFICIENT);
    }

    private void givenSizeGrowArrayIfFull(int necessarySize) {
        grow(necessarySize);
    }

    private void grow(int size) {
        Object[] enlargedElements = new Object[size];
        copyElements(elements, ARRAY_BEGINNING_INDEX,
                    enlargedElements, ARRAY_BEGINNING_INDEX, elements.length);
        elements = enlargedElements;
    }

    private boolean checkIndexGreaterEqualSize(int index) {
        return index >= size;
    }

    private boolean checkForNegativeIndex(int index) {
        return index < ARRAY_BEGINNING_INDEX;
    }

    private void increaseSize(int value) {
        size = size + value;
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

    private void copyElements(Object[] copyFromArray, int startIndexFromArray,
                              Object[] copyToArray, int startIndexToArray,
                              int numberOfElementsToCopy) {
        System.arraycopy(copyFromArray, startIndexFromArray,
                copyToArray, startIndexToArray, numberOfElementsToCopy);
    }
}
