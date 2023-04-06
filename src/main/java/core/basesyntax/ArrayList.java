package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private T[] grow(int oldSize) {
        int newSize = oldSize + oldSize / 2;
        T[] newElementData = (T[]) new Object[newSize];
        System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
        return newElementData;
    }

    public void checkNonExistentOrNegativePosition(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element on this position");
        }
    }

    public void checkIndexForAddElement(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element on this position");
        }
    }

    public void addElementInsideArrayByIndex(T value, int index) {
        T[] newElementData = (T[]) new Object[size + 1];
        if ((size + 1) > elementData.length) {
            grow(size + 1);
        }
        if (index == 0) {
            System.arraycopy(elementData, 0, newElementData, 1, size);
            elementData = newElementData;
            elementData[index] = value;
            size++;
        } else {
            System.arraycopy(elementData, 0, newElementData, 0, index);
            System.arraycopy(elementData, index, newElementData, index + 1,
                    size - index);
            elementData = newElementData;
            elementData[index] = value;
            size++;
        }
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAddElement(index);
        if (index == elementData.length) {
            elementData = grow(size);
        }
        if (index >= 0 && index < size) {
            addElementInsideArrayByIndex(value, index);
            return;
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if ((size + list.size()) > elementData.length) {
            elementData = grow(size + list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkNonExistentOrNegativePosition(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkNonExistentOrNegativePosition(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkNonExistentOrNegativePosition(index);
        T[] newElementData = (T[]) new Object[size - 1];
        T removedElement = elementData[index];
        if (index == 0) {
            System.arraycopy(elementData, 1, newElementData, 0, size - 1);
            elementData = newElementData;
            size--;
        } else {
            System.arraycopy(elementData, 0, newElementData, 0, index);
            System.arraycopy(elementData, index + 1, newElementData, index,
                    size - 1 - index);
            elementData = newElementData;
            size--;
        }
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if ((elementData[i] == element)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't remove non existent value!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
