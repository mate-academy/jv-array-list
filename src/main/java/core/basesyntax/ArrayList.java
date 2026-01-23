package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private Object[] elementData = new Object[DEFAULT_CAPACITY];
    private int size;

    private void grow() {
        int newCapacity = (int) (elementData.length * GROWTH_FACTOR);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, elementData.length);
        elementData = newArray;
    }

    // Хелпер для перевірки індексу при додаванні (0 <= index <= size)
    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid position index: " + index);
        }
    }

    // Хелпер для перевірки індексу при отриманні/видаленні (0 <= index < size)
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid element index: " + index);
        }
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkPositionIndex(index);
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkElementIndex(index);
        final T removedValue = (T) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
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
