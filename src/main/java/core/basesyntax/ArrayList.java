package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData = new Object[DEFAULT_CAPACITY];
    private int size;

    public ArrayList() {

        Object[] elementData = new Object[DEFAULT_CAPACITY];
    }

    private Object[] grow(int currentCapacity) {
        int newCapacity = currentCapacity + (currentCapacity >> 1);
        return Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow(size);
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < size) {
            return;
        }
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("ArrayListIndexOutOfBoundsException");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("ArrayListIndexOutOfBoundsException");
        }
        elementData[index] = value;

    }

    @Override
    public T remove(int index) {
        size--;
        return null;
    }

    @Override
    public T remove(T element) {
        size--;
        return null;
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
