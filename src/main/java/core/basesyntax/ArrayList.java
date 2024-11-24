package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_INDEX = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList() {
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
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = size + list.size();
        if (newSize > elementData.length) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return elementData[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", is invalid ");
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", is invalid ");
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T deleteValue = elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
            elementData[--size] = null;
            return deleteValue;
        }
        throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == null && element == null) {
                return remove(i);
            }
            if (elementData[i] != null && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element: " + element + " not found");
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
        int newCapacity = (int)(size * GROW_INDEX);
        T[] newElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }
}
