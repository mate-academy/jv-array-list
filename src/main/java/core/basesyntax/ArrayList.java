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
        checkIndexForAdd(index);
        grow();
        System.arraycopy(elementData, index, elementData, index + OFFSET, size - index);
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
        final T oldValue = elementData[index];
        index += 1;
        System.arraycopy(elementData, index, elementData, index - OFFSET, size - index);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i]
                    || element != null && element.equals(elementData[i])) {
                return remove(i);
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
        if (size == elementData.length) {
            int newCapacity = (int) (elementData.length * ELEMENT_DATA_MULTIPLIER);
            T[] expandedElementData = (T[]) new Object[newCapacity];
            System.arraycopy(elementData, START_INDEX, expandedElementData, START_INDEX, size);
            elementData = expandedElementData;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < START_INDEX) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < START_INDEX) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }
}
