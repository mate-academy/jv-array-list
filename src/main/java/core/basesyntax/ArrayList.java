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
        elementData = growIfArrayFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        growIfArrayFull();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        elementData = growIfArrayFull();
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
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = get(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elementData[i] != null && elementData[i].equals(element))
                    || elementData[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element present in the list.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] growIfArrayFull() {
        T[] grownArray = elementData;
        int oldCapacity = elementData.length;
        if (size < oldCapacity) {
            return grownArray;
        }
        int newCapacity = elementData.length + (elementData.length >> 1);
        grownArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, grownArray, 0, size);
        elementData = grownArray;
        return grownArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index
                    + ". Size: " + size);
        }
    }
}
