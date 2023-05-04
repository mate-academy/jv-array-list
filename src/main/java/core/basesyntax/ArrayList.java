package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_MULTIPLIER = 1.5;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        elementData[size] = value;
        size++;
        if (size == elementData.length) {
            grow();
        }
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            elementData[index] = value;
            size++;
            return;
        }
        checkIndex(index);
        System.arraycopy(elementData, index, elementData, index + 1,
                elementData.length - (index + 1));
        elementData[index] = value;
        size++;
        if (size == elementData.length) {
            grow();
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
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        this.elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object removedElement = elementData[index];
        System.arraycopy(elementData, index + 1,
                elementData, index, elementData.length - (index + 1));
        size--;
        return (T) removedElement;
    }

    @Override
    public T remove(T element) {
        remove(checkElement(element));
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void grow() {
        Object[] updatedData = new Object[(int) (elementData.length * CAPACITY_MULTIPLIER)];
        System.arraycopy(elementData, 0, updatedData, 0, size);
        elementData = updatedData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of range " + size);
        }
    }

    private int checkElement(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == elementData[i])
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return i;
            }
        }
        throw new NoSuchElementException("Element: " + element + " is not found.");
    }
}
