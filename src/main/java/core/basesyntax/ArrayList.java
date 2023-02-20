package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size = 0;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (checkIndex(index) && index == size) {
            grow();
            elementData[index] = value;
            size++;
        } else {
            T[] newElementData = (T[]) new Object[elementData.length];
            System.arraycopy(elementData, 0, newElementData, 0, index);
            newElementData[index] = value;
            System.arraycopy(elementData, index, newElementData, index + 1, size);
            System.arraycopy(newElementData, 0, elementData, 0, size);
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (elementData.length - size < list.size()) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (checkIndex(index)) {
            return elementData[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        T value = null;
        if (checkIndex(index)) {
            value = elementData[index];
            T[] newElementData = (T[]) new Object[elementData.length];
            System.arraycopy(elementData, 0, newElementData, 0, index);
            System.arraycopy(elementData, index + 1, newElementData, index, size);
            System.arraycopy(newElementData, 0, elementData, 0, size);
            size--;
        }
        return value;
    }

    @Override
    public T remove(T element) {
        T value = null;
        int index = -1;
        if (null == element) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    index = i;
                    break;
                }
            }
            value = checkRemoveIndex(index);
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i])) {
                    index = i;
                    break;
                }
            }
            value = checkRemoveIndex(index);
        }
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean chekArraySize() {
        if (size + 1 == newCapacity) {
            return false;
        } else {
            return true;
        }
    }

    private void validIndex(int index) {
        if (((isEmpty()) && (index != 0)) || ((!isEmpty()) && (index < 0 || index >= size))) {
            throw new ArrayListIndexOutOfBoundsException("Not valid index");
        }
        if (index == 0) {
            startIndex = true;
        }
        if (index + 1 == size) {
            lastIndex = true;
        }
    }

    private void updateLengthArray() {
        T[] list = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, list, 0, size);
        elementData = (T[]) new Object[newCapacity];
        System.arraycopy(list, 0, elementData, 0, size);
    }

    private void addValueToIndex(T value, int index) {
        T[] list = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, list, 0, index);
        list[index] = value;
        size++;
        System.arraycopy(elementData, index, list, index + 1, size);
        System.arraycopy(list, 0, elementData, 0, size);
    }

    private void removeValue(T value, int index) {
        T[] list = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, list, 0, index);
        size--;
        System.arraycopy(elementData, index + 1, list, index, size);
        System.arraycopy(list, 0, elementData, 0, size);
    }

    private void copyValueInArray(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            elementData[size + i] = list.get(i);
        }
        size += list.size();
    }

    private void setNewCapacity() {
        newCapacity = (int) (size * 1.5) + size;
    }

    private void copyArraysAfterRemove(T[] list) {
        size--;
        elementData = (T[]) new Object[size];
        System.arraycopy(list, 0, elementData, 0, size);
    }
}
