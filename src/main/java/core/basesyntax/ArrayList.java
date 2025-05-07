package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double CAPACITY_MULTIPLIER = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("invalid index " + index + " entered."
                    + " Index must be between 0 and " + size);
        }
        resize();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resize();
            elementData[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkingIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkingIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkingIndex(index);
        resize();
        T result = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null ? element == null : elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("no such element present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (size == elementData.length) {
            int newCapacity = (int) (elementData.length * CAPACITY_MULTIPLIER);
            T[] newElementData = (T[]) new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
            elementData = newElementData;
        }
    }

    private void checkingIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("invalid index " + index + " entered."
                    + " Index must be between 0 and " + size);
        }
    }
}
