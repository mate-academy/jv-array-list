package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private static final int DEFAULT_INDEX = 0;
    private static final String INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE
            = "Index out of bounds: ";
    private static final String NO_SUCH_ELEMENT_EXCEPTION_MESSAGE = "Element not found in the list";
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        growIfArrayFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE + index);
        }
        growIfArrayFull();
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
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == null && element == null)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArray(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, DEFAULT_INDEX, newArray, DEFAULT_INDEX, size);
        elementData = newArray;
    }

    private void growIfArrayFull() {
        if (size == elementData.length) {
            int newCapacity = (int) (elementData.length * GROWTH_FACTOR);
            resizeArray(newCapacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE + index);
        }
    }
}
