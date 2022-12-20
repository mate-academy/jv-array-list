package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double CAPACITY_COEFFICIENT = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ARRAY_START_POSITION_INDEX = 0;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        grow();
        System.arraycopy(elementData, index, elementData,
                index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            grow();
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object removeElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData,
                index, size - index - 1);
        size--;
        return (T) removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null && element.equals(elementData[i])
                    || element == null && elementData[i] == null) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("This element was not found " + element);
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
            int newCapacity = (int) (elementData.length * CAPACITY_COEFFICIENT);
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, ARRAY_START_POSITION_INDEX, newElementData,
                    ARRAY_START_POSITION_INDEX, size);
            elementData = newElementData;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid Index: " + index);
        }
    }
}
