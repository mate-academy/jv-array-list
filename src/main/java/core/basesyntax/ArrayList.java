package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    static final int DEFAULT_ARRAY_SIZE = 10;
    private Object[] elementData = new Object[DEFAULT_ARRAY_SIZE];
    private int size = 0;

    public Object[] getElementData() {
        return elementData;
    }

    public void setElementData(Object[] elementData) {
        this.elementData = elementData;
    }

    public Object[] grow() {
        Object[] newArray = new Object[elementData.length + elementData.length / 2];
        System.arraycopy(elementData, 0, newArray, 0, size);
        return newArray;
    }

    public boolean indexCheck(int index) {
        return (index < size && index >= 0) || index == 0;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == elementData.length) {
            elementData = grow();
        }
        if (index == size) {
            add(value);
        } else if (indexCheck(index)) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }

    @Override
    public void addAll(List<T> list) {
        do {
            elementData = grow();
        } while (size + list.size() > elementData.length);
        for (int i = 0; i < list.size(); i++) {
            elementData[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (indexCheck(index)) {
            return (T) elementData[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("index is invalid");
        }
    }

    @Override
    public void set(T value, int index) {
        if (indexCheck(index)) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("index is invalid");
        }
    }

    @Override
    public T remove(int index) {
        if (indexCheck(index)) {
            Object removed = elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
            elementData[--size] = null;
            return (T)removed;
        } else {
            throw new ArrayListIndexOutOfBoundsException("index is invalid");
        }
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == element)
                    || (elementData[i] != null
                    && elementData[i].equals(element))) {
                index = i;
                break;
            }
        }
        if (index != - 1) {
            return remove(index);
        } else {
            throw new NoSuchElementException("Element to remove not present");
        }
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
