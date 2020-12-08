package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            enlargeCapacity();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == elementData.length) {
            enlargeCapacity();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isCorrectIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        isCorrectIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        isCorrectIndex(index);
        T removedElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T t) {
        int index = findElement(t);
        if (index == - 1) {
            return t;
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void enlargeCapacity() {
        Object[] newElementData = new Object[elementData.length + (elementData.length >> 1)];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    private void isCorrectIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Incorrect index - " + index);
        }
    }

    private int findElement(T elementToFind) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == elementToFind || elementData[i].equals(elementToFind)) {
                return i;
            }
        }
        throw new NoSuchElementException("Element not found " + elementToFind);
    }
}
