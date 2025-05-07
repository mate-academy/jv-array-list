package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float MAGNIFICATION_FACTOR = 1.5F;
    private int size;
    private Object[] elementsData;

    public ArrayList() {
        this.elementsData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        elementsData = ensureCapacity(1);
        elementsData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || size < index) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect");
        }
        elementsData = ensureCapacity(1);
        System.arraycopy(elementsData, index, elementsData, index + 1, size - index);
        elementsData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int previousSize = size();
        elementsData = ensureCapacity(list.size());
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final Object removedElement = elementsData[index];
        System.arraycopy(elementsData, index + 1, elementsData, index, size - 1 - index);
        elementsData[--size] = null;
        return (T) removedElement;
    }

    @Override
    public T remove(T element) {
        int index = findIndexOfElement(element);
        checkIndex(index);
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

    private Object[] grow() {
        Object[] biggerArray = new Object[(int) (elementsData.length * MAGNIFICATION_FACTOR)];
        System.arraycopy(elementsData, 0, biggerArray, 0, size);
        return biggerArray;
    }

    private Object[] grow(int elementsToAdd) {
        Object[] biggerArray = new Object[elementsData.length + elementsToAdd];
        System.arraycopy(elementsData, 0, biggerArray, 0, size);
        return biggerArray;
    }

    private Object[] ensureCapacity(int elementsToAdd) {
        if (elementsData.length >= size + elementsToAdd) {
            return elementsData;
        } else if (elementsToAdd + elementsData.length
                > elementsData.length * MAGNIFICATION_FACTOR) {
            return grow(elementsToAdd);
        } else {
            return grow();
        }
    }

    private void checkIndex(int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index " + index);
        }
    }

    private int findIndexOfElement(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elementsData[i] == element || element != null
                    && element.equals(elementsData[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("Given element is not found in current list");
    }
}
