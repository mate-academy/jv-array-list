package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};
    private Object[] objectsArray = EMPTY_ARRAY;
    private int capacity;

    private Object[] grow() {
        int oldCapacity = objectsArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1) + 1;
        if (oldCapacity > 0) {
            return objectsArray = Arrays.copyOf(objectsArray, newCapacity);
        }
        return objectsArray = new Object[Math.max(DEFAULT_ARRAY_CAPACITY, newCapacity)];
    }

    private void indexInBoundsCheck(int index) {
        if (index >= capacity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
    }

    public int getIndexFromArrayOfRequiredElement(T element) {
        for (int i = 0; i < capacity; i++) {
            if (element == objectsArray[i] || element != null && element.equals(objectsArray[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add(T value) {
        add(value, capacity);
    }

    @Override
    public void add(T value, int index) {
        if (index > capacity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        if (index == objectsArray.length) {
            objectsArray = grow();
        }
        if (index < capacity) {
            Object[] buffer = new Object[capacity - index];
            System.arraycopy(objectsArray, index, buffer, 0, capacity - index);
            objectsArray[index] = value;
            System.arraycopy(buffer, 0, objectsArray, index + 1, buffer.length);
        } else {
            objectsArray[index] = value;
        }
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
        indexInBoundsCheck(index);
        return (T) objectsArray[index];
    }

    @Override
    public void set(T value, int index) {
        indexInBoundsCheck(index);
        objectsArray[index] = value;
    }

    @Override
    public T remove(T element) {
        if (getIndexFromArrayOfRequiredElement(element) == -1) {
            throw new NoSuchElementException("Element does not exist.");
        }
        return remove(getIndexFromArrayOfRequiredElement(element));
    }

    @Override
    public T remove(int index) {
        indexInBoundsCheck(index);
        Object oldElement = objectsArray[index];
        System.arraycopy(objectsArray, index + 1, objectsArray,
                index, capacity - index - 1);
        capacity--;
        return (T) oldElement;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return capacity == 0;
    }
}
