package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int GROWING_NUMBER = 5;
    private static final int FIRST_INDEX = 0;
    private static final int DEFAULT_LENGTH = 10;
    private int size = 0;
    private T[] elementData;
    private T element;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_LENGTH];
    }

    public T[] growIfArrayFull() {
        T[] newElementData = (T[]) new Object[elementData.length + GROWING_NUMBER];
        System.arraycopy(elementData, FIRST_INDEX, newElementData, FIRST_INDEX, size());
        return newElementData;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = growIfArrayFull();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        if (size == elementData.length) {
            elementData = growIfArrayFull();
        }
        if (index != size) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > elementData.length) {
            growIfArrayFull();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        element = elementData[index];
        elementData[index] = null;
        for (int i = index + 1; i < size; i++) {
            elementData[i - 1] = elementData[i];
        }
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (elementData[i] == null && element == null
                    || elementData[i] != null && elementData[i].equals(element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element in list: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size() > 0) {
            return false;
        }
        return true;
    }
}
