package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_COEFFICIENT = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.size = 0;
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
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
        checkAddRange(index);
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

    private int calculateNewCapacity() {
        return (int) (elementData.length * INCREASE_COEFFICIENT);
    }

    private void grow() {
        T[] newElementData = (T[]) new Object[calculateNewCapacity()];
        System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
        elementData = newElementData;
    }

    @Override
    public T get(int index) {
        checkRange(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkRange(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        T result = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found in list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    private void checkAddRange(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }
}
