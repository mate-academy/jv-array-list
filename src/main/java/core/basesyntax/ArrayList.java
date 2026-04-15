package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_INDEX = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        if (size >= capacity) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < START_INDEX) {
            throw new ArrayListIndexOutOfBoundsException("index %s out of bounds for length %s"
                    .formatted(index, size));
        }
        if (size >= capacity) {
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
        T value = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i]
                    || (elementData[i] != null && elementData[i].equals(element))) {
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

    private void grow() {
        capacity += capacity / 2;
        T[] newElementData = (T[]) new Object[capacity];
        System.arraycopy(elementData, START_INDEX, newElementData, START_INDEX, size);
        elementData = newElementData;
    }

    private void checkIndex(int index) {
        if (index > size - 1 || index < START_INDEX) {
            throw new ArrayListIndexOutOfBoundsException("index %s out of bounds for length %s"
                    .formatted(index, size));
        }
    }
}
