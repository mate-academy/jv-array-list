package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object [] elementData;
    private int size;
    private int capacity = DEFAULT_CAPACITY;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (capacity == size) {
            increaseCapacity();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds index " + index);
        }
        if (capacity == size) {
            increaseCapacity();
        }
        if (index <= size) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        }
    }

    public void increaseCapacity() {
        capacity *= 1.5;
        Object [] newElementData = new Object[capacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds index " + index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds index " + index);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        if (index < size) {
            elementData[index] = value;
        }
        if (index > size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds index " + index);
        }
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = (T) elementData[index];
        int numberMoved = size - index - 1;
        if (numberMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numberMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in the list.");
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
