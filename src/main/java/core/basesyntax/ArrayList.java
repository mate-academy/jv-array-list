package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_INDEX = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfNeeded();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        growIfNeeded();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("The provided list cannot be null because"
                    + " elements must be added from a valid list.");
        }

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elementData[i] == null) {
                    return remove(i);
                }
            } else if (elementData[i] != null && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " is not present in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfNeeded() {
        if (size == elementData.length) {
            resizeArray();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + ". Valid indices are between 0 and " + (size - 1)
                    + ", list size is " + size);

        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + ". Valid indices are between 0 and " + (size)
                    + ", list size is " + size);
        }
    }

    private void resizeArray() {
        int newCapacity = (int) (elementData.length * CAPACITY_INDEX);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }
}
