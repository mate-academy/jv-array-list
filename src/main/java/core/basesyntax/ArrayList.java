package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_MULTIPLIER = 1.5;

    private T[] elementsData;
    private int size;

    public ArrayList() {
        elementsData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elementsData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        resizeCheck(index);
        ensureCapacity(size + 1);
        System.arraycopy(elementsData, index, elementsData, index + 1, size - index);
        elementsData[index] = value;
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
        indexExistence(index);
        return (T) elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        indexExistence(index);
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexExistence(index);
        T removedElement = (T) elementsData[index];
        System.arraycopy(elementsData, index + 1, elementsData, index, size - index - 1);
        elementsData[--size] = null; // Clear the last element
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elementsData[i] == null) || (element != null
                    && element.equals(elementsData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element" + element + "not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexExistence(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    private void resizeCheck(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index for add operation: "
                    + index);
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementsData.length) {
            int newCapacity = (int) (elementsData.length * CAPACITY_MULTIPLIER);
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(elementsData, 0, newArray, 0, elementsData.length);
            elementsData = newArray;
        }
    }
}
