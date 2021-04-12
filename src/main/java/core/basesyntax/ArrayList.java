package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String ARRAY_OUT_OF_BOUND_MESSAGE = "This index is out "
            + "of the array range...";
    private static final String NO_SUCH_ELEMENT_MESSAGE = "There is no such element...";
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            elements = resizeArray();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == elements.length) {
            elements = resizeArray();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(ARRAY_OUT_OF_BOUND_MESSAGE);
        }
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
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T deletedElementByIndex = elements[index];
        int numberOfElements = elements.length - (index + 1);
        System.arraycopy(elements, index + 1, elements, index, numberOfElements);
        size--;
        return deletedElementByIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (element == elements[i] || element != null
                    && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT_MESSAGE);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(ARRAY_OUT_OF_BOUND_MESSAGE);
        }
    }

    private T[] resizeArray() {
        T[] extendedArray = (T[]) new Object[(size * 3) / 2];
        System.arraycopy(elements, 0, extendedArray, 0, size);
        return extendedArray;
    }
}
