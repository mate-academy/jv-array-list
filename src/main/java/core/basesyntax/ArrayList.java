package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentCapacity = 10;
    private int size = 0;
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        if (size == currentCapacity - 1) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (size == currentCapacity - 1) {
            elementData = grow();
        }
        T[] newElementData = (T[]) new Object[currentCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, index);
        newElementData[index] = value;
        System.arraycopy(elementData, index, newElementData, index + 1, size - index);
        size++;
        System.arraycopy(newElementData, 0, elementData, 0, size);
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() + size >= currentCapacity) {
            elementData = grow();
        }
        T[] listToArray = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listToArray[i] = list.get(i);
        }
        System.arraycopy(listToArray, 0, elementData, size, list.size());
        size += list.size();
    }

    @Override
    public T get(int index) {
        rangeCheckForGet(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForGet(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForGet(index);
        T oldValue = elementData[index];
        removeOneElement(index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] != null) {
                    remove(i);
                    return null;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (elementData[i] != null && elementData[i].equals(element)) {
                removeOneElement(i);
                return element;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        currentCapacity += currentCapacity / 2;
        T[] newElementData = (T[]) new Object[currentCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        return newElementData;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }
    }

    private void rangeCheckForGet(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }
    }

    private void removeOneElement(int index) {
        T[] newElementData = (T[]) new Object[currentCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, index);
        System.arraycopy(elementData, index + 1, newElementData, index, size - index - 1);
        size--;
        System.arraycopy(newElementData, 0, elementData, 0, size);
    }

}
