package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private Object[] elementData = new Object[DEFAULT_INITIAL_CAPACITY];
    private int size;

    public ArrayList() {
        Object[] elementData = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        indexRangeCheck(index);
        grow();
        System.arraycopy(elementData, index, elementData, index + 1,size - index);
        elementData[index] = value;
        size = size + 1;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexRangeCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexRangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexRangeCheck(index);
        T oldValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || elementData[i] != null
                    && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element #" + element + " is no present.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (size == elementData.length) {
            int newCapacity = size + (size >> 1); /* 1.5 times growth */
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private void indexRangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index #" + index + " is invalid.");
        }
    }
}

