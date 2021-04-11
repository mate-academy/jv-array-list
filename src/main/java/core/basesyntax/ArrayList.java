package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (capacity + 1 > elementData.length) {
            grow();
        }
        elementData[capacity++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > capacity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Given index is out of bound");
        }
        if (capacity == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, capacity - index);
        elementData[index] = value;
        capacity++;
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
        final Object oldValue = elementData[index];
        checkIndex(index);
        System.arraycopy(elementData, index + 1, elementData, index, capacity - 1);
        capacity--;
        return (T) oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < capacity; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element exists");
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return capacity == 0;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[capacity + capacity / 2 + 1];
        System.arraycopy(elementData, 0, newArray, 0, elementData.length);
        elementData = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= capacity) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }
}
