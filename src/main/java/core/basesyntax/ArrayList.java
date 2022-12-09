package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    private void checkSize() {
        if (size == elementData.length) {
            grow();
        }
    }

    private void grow() {
        int newCapacity = (int) (elementData.length * GROWTH_FACTOR);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData, 0,
                newArray, 0, size);
        elementData = newArray;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid Index: " + index);
        }
    }

    @Override
    public void add(T value) {
        checkSize();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkElementIndex(index);
        checkSize();
        System.arraycopy(elementData, index,
                elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            checkSize();
            elementData[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkElementIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        size--;
        Object element = elementData[index];
        if (size > index) {
            System.arraycopy(elementData, index + 1, elementData,
                    index, size - index);
        }
        elementData[size] = null;
        return (T) element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elementData[i] == null)
                    || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
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
