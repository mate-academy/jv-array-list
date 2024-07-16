package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.size = 0;
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIfIndexOutOfBoundsForAdd(index);
        resize();
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

    @Override
    public T get(int index) {
        checkIfIndexOutOfBounds(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexOutOfBounds(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexOutOfBounds(index);
        T removedElement = elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < size; index++) {
            if (element == null ? elementData[index] == null : element.equals(elementData[index])) {
                return remove(index);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (elementData.length == size) {
            int newCapacity = (int) (elementData.length * GROWTH_FACTOR);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = (T[]) newArray;
        }
    }

    private void checkIfIndexOutOfBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + ". Valid range: 0 to " + (size - 1));
        }
    }

    private void checkIfIndexOutOfBoundsForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index
                    + ". Valid range: 0 to "
                    + size);
        }
    }
}
