package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int OFFSET = 1;
    private static final int START_INDEX = 0;
    private static final int INITIAL_CAPACITY = 10;
    private static final double ELEMENT_DATA_MULTIPLIER = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < START_INDEX) {
            checkIndex(index);
        }
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + OFFSET, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() >= elementData.length) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
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
        final T oldValue = elementData[index];
        index += 1;
        System.arraycopy(elementData, index, elementData, index - OFFSET, size - index);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elementData[i] == null)
                    || (element != null && element.equals(elementData[i]))) {
                return removeAt(i);
            }
        }
        throw new NoSuchElementException("No such element in list: " + element);
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
        int newCapacity = (int) (elementData.length * ELEMENT_DATA_MULTIPLIER);
        T[] expandedElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, START_INDEX, expandedElementData, START_INDEX, size);
        elementData = expandedElementData;
    }

    private void checkIndex(int index) {
        if (index >= size || index < START_INDEX) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

    private T removeAt(int index) {
        T removedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removedElement;
    }
}
