package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size == elementData.length) {
            elementData = grow();
        }
        size++;
        checkIndexInBounds(index);
        System.arraycopy(elementData, index, elementData, index + 1, size - index - 1);
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T elem = (T) list.get(i);
            add(elem);
        }
    }

    private T[] grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        T[] newElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, oldCapacity);
        return newElementData;
    }

    @Override
    public T get(int index) {
        checkIndexInBounds(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexInBounds(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexInBounds(index);
        T value = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] != null && elementData[i].equals(element)
                    || elementData[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element was not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexInBounds(int index) {
        if (index != 0 && (index < 0 || index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " is out of bounds: " + size);
        }
    }
}
