package core.basesyntax;

import static java.lang.System.arraycopy;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    private Object[] grow() {
        T[] newElementData = (T[]) new Object[elementData.length + (elementData.length >> 1)];
        arraycopy(elementData, 0, newElementData,
                0, elementData.length);
        return newElementData;
    }

    @Override
    public void add(Object value) {
        if (size + 1 == elementData.length) {
            elementData = (T[]) grow();
        }
        elementData[size] = (T) value;
        size++;
    }

    @Override
    public void add(Object value, int index) {

        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }

        if (size + 1 == elementData.length) {
            elementData = (T[]) grow();
        }

        T[] temporaryArray = (T[]) new Object[size - index];
        arraycopy(elementData, index, temporaryArray,
                0, size - index);
        elementData[index] = (T) value;
        arraycopy(temporaryArray, 0, elementData,
                index + 1, temporaryArray.length);
        size++;
    }

    @Override
    public void addAll(List list) {
        T[] listToArray = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listToArray[i] = (T) list.get(i);
        }
        int listLength = listToArray.length;
        while (size + listToArray.length > elementData.length) {
            elementData = (T[]) grow();
        }
        arraycopy(listToArray, 0, elementData, size, listLength);
        size += listLength;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        } else {
            return elementData[index];
        }
    }

    @Override
    public void set(Object value, int index) {
        if (index >= 0 && index <= size - 1) {
            elementData[index] = (T) value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
    }

    @Override
    public Object remove(int index) {

        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        final T removedElement = elementData[index];
        T[] temporaryArray = (T[]) new Object[size - index];
        arraycopy(elementData, index + 1, temporaryArray,
                0, size - index);
        arraycopy(temporaryArray, 0, elementData,
                index, temporaryArray.length);
        elementData[size] = null;
        size--;
        return removedElement;
    }

    @Override
    public Object remove(Object element) {

        boolean isContain = false;
        int searchedIndex = 0;
        for (int i = 0; i < size; i++) {

            if (Objects.equals(element,elementData[i])) {
                searchedIndex = i;
                isContain = true;
                break;
            }
        }

        if (!isContain) {
            throw new NoSuchElementException("There is no such element present");
        }
        T searchedElement = elementData[searchedIndex];
        if (isContain) {

            T[] temporaryArray = (T[]) new Object[size - searchedIndex];
            arraycopy(elementData, searchedIndex + 1, temporaryArray,
                    0, size - searchedIndex); // 4-7
            arraycopy(temporaryArray, 0, elementData,
                    searchedIndex, temporaryArray.length);
            elementData[size] = null;
            size--;

        }
        return searchedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}

