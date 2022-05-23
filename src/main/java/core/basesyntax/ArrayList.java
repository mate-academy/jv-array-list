package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int arraySize = 0;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elements[arraySize] = value;
        arraySize++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > arraySize) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index should be in the range 0 - " + arraySize  + " but index was: " + index);
        }
        grow();
        System.arraycopy(elements, index, elements, index + 1,
                arraySize - index);
        elements[index] = value;
        arraySize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        check(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        check(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        check(index);
        T removedValue = elements[index];
        if (index != elements.length - 1) {
            System.arraycopy(elements, index + 1, elements, index, arraySize - index);
        } else {
            elements[index] = null;
        }
        arraySize--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arraySize; i++) {
            if (element == null && elements[i] == null
                        || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in the list");
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    private void grow() {
        if (elements.length == arraySize) {
            int newCapacity = elements.length + (elements.length >> 1);
            Object[] increasedElements = new Object[newCapacity];
            System.arraycopy(elements, 0, increasedElements, 0, elements.length);
            elements = (T[]) increasedElements;
        }
    }

    private void check(int index) {
        if (index >= arraySize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
    }
}
