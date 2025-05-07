package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            ensureCapacity();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (size == elementData.length) {
            ensureCapacity();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > elementData.length) {
            ensureCapacity();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i), size);
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        final T removedElement = (T) elementData[index];
        int elementsToMove = size - index - 1;
        if (elementsToMove > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, elementsToMove);
        }
        elementData[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || elementData[i] != null && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds"
                    + System.lineSeparator()
                    + "Index: " + index + System.lineSeparator()
                    + "Size: " + size);
        }
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds"
                    + System.lineSeparator()
                    + "Index: " + index + System.lineSeparator()
                    + "Size: " + size);
        }
    }

    private void ensureCapacity() {
        int newCapacity = elementData.length + (elementData.length >> 1);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, elementData.length);
        elementData = newArray;
    }
}
