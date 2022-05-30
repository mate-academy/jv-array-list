package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public void add(T value) {
        grow();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        addException(value, index);
        grow();
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
        getException(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        setException(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        removeException(index);
        T removedElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, --size - index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        return remove(findElement(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int findElement(T element) {
        int remElement = -1;
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || elementData[i] != null && elementData[i].equals(element)) {
                remElement = i;
            }
        }
        if (remElement < 0) {
            throw new NoSuchElementException("Cannot find element: " + element);
        }
        return remElement;
    }

    private void grow() {
        if (elementData.length <= size) {
            Object[] newElementsData = new Object[elementData.length + (elementData.length >> 1)];
            System.arraycopy(elementData, 0, newElementsData, 0, elementData.length);
            elementData = newElementsData;
        }
    }

    private void addException(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Cannot add value "
                    + value + " with index "
                    + index);
        }
    }

    private void getException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Cannot give value from index: " + index);
        }
    }

    private void setException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Cannot set index: " + index);
        }
    }

    private void removeException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Cannot remove element by index: "
                    + index);
        }
    }
}
