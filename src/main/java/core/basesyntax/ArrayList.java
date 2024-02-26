package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Index out of bounds: ";
    private static final String ELEMENT_NOT_FOUND_MESSAGE = "Element not found: ";
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_MULTIPLIER = 1.5;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        validateAddIndex(index);
        ensureCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
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
        validateIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T removedElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (areElementsEquals((T) elementData[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(ELEMENT_NOT_FOUND_MESSAGE + element);
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
        int newCapacity = (elementData.length == 0)
                ? DEFAULT_CAPACITY : (int) Math.round(elementData.length * CAPACITY_MULTIPLIER);
        Object[] newElementData = new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        return newElementData;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE + index);
        }
    }

    private void validateAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE + index);
        }
    }

    private boolean areElementsEquals(T element1, T element2) {
        return element1 == element2 || (element1 != null && element1.equals(element2));
    }

    private void ensureCapacity() {
        if (size == elementData.length) {
            elementData = grow();
        }
    }
}
