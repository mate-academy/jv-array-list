package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY_FOR_ARRAY = 10;
    private static final String INDEX_ERROR_MESSAGE =
            "The passed index is not allowed";
    private static final String ELEMENT_NOT_FOUND_MESSAGE = "Element not found";
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY_FOR_ARRAY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            resize();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        validateIndex(index, true);
        if (size == elementData.length) {
            resize();
        }
        if (size == index) {
            add(value);
        } else {
            System.arraycopy(elementData, index, elementData, index + 1,
                    size - index);
            set(value, index);
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        while (size + listSize > elementData.length) {
            resize();
        }
        System.arraycopy(toArray(list), 0, elementData, size, listSize);
        size += listSize;
    }

    @Override
    public T get(int index) {
        validateIndex(index, false);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index, false);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index, false);
        final T element = this.get(index);
        System.arraycopy(elementData, index + 1, elementData, index,
                size - index - 1);
        size--;
        elementData[size] = null;
        return element;
    }

    @Override
    public T remove(T element) {
        int index = findIndexOfElementToRemove(element);
        System.arraycopy(elementData, index + 1, elementData, index,
                size - index - 1);
        size--;
        elementData[size] = null;
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

    private void resize() {
        Object[] oldArray = elementData;
        int newCapacityForArray = oldArray.length + (oldArray.length >> 1);
        elementData = new Object[newCapacityForArray];
        System.arraycopy(oldArray, 0, elementData, 0, oldArray.length);
    }

    private Object[] toArray(List<T> list) {
        int listSize = list.size();
        Object[] result = new Object[listSize];
        for (int i = 0; i < listSize; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private void validateIndex(int index, boolean isForAdd) {
        if (isForAdd && (index < 0 || index > size)) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR_MESSAGE);
        }
        if (!isForAdd && (index < 0 || index >= size)) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR_MESSAGE);
        }
    }

    private int findIndexOfElementToRemove(T element) {
        for (int i = 0; i < size; i++) {
            T currentValue = this.get(i);
            if (element == currentValue
                    || (element != null && element.equals(currentValue))) {
                return i;
            }
        }
        throw new NoSuchElementException(ELEMENT_NOT_FOUND_MESSAGE);
    }
}
