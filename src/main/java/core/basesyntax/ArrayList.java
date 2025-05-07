package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData = new Object[DEFAULT_CAPACITY];
    private int size;

    public ArrayList() {
        Object[] elementData = new Object[DEFAULT_CAPACITY];
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
        checkIndexBounds(index);
        grow();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
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
        checkIndexBounds(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBounds(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(index);
        T oldValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = 0;
        while (index < size) {
            if (element == elementData[index]
                    || element != null && element.equals(elementData[index])) {
                return remove(index);
            }
            index++;
        }
        throw new NoSuchElementException("NoSuchElementException, element " + element);
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
            int newCapacity = size + (size >> 1);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private void checkIndexBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " not found.");
        }
    }
}
