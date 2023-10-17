package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY_FOR_ARRAY = 10;
    private static final String INDEX_ERROR_MESSAGE =
            "The passed index is not allowed";
    private static final String ELEMENT_NOT_FOUND_MESSAGE = "Element not found";
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY_FOR_ARRAY];
    }

    @Override
    public void add(T value) {
        checkSize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        checkSize();
        if (size == index) {
            add(value);
        } else {
            System.arraycopy(elements, index, elements, index + 1,
                    size - index);
            elements[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        while (size + listSize > elements.length) {
            resize();
        }
        System.arraycopy(toArray(list), 0, elements, size, listSize);
        size += listSize;
    }

    @Override
    public T get(int index) {
        validateIndexNotForAdd(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndexNotForAdd(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndexNotForAdd(index);
        final T element = (T) elements[index];
        removeElement(index);
        return element;
    }

    @Override
    public T remove(T element) {
        int index = findIndexOfElement(element);
        removeElement(index);
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    private void checkSize() {
        if (size == elements.length) {
            resize();
        }
    }

    private void resize() {
        Object[] oldArray = elements;
        elements = new Object[oldArray.length + (oldArray.length >> 1)];
        System.arraycopy(oldArray, 0, elements, 0, oldArray.length);
    }

    private Object[] toArray(List<T> list) {
        int listSize = list.size();
        Object[] result = new Object[listSize];
        for (int i = 0; i < listSize; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR_MESSAGE);
        }
    }

    private void validateIndexNotForAdd(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR_MESSAGE);
        }
    }

    private int findIndexOfElement(T element) {
        for (int i = 0; i < size; i++) {
            T currentValue = (T) elements[i];
            if (element == currentValue
                    || (element != null && element.equals(currentValue))) {
                return i;
            }
        }
        throw new NoSuchElementException(ELEMENT_NOT_FOUND_MESSAGE);
    }

    private void removeElement(int index) {
        System.arraycopy(elements, index + 1, elements, index,
                size - index - 1);
        elements[--size] = null;
    }
}
