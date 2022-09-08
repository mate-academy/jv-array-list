package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Such index " + index + " doesn't exist");
        }
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] newArrayList = new Object[elementData.length + list.size()];
        System.arraycopy(elementData, 0, newArrayList, 0, size);
        for (int i = 0; i < list.size(); i++) {
            newArrayList[size+i] = list.get(i);
        }
        elementData = newArrayList;
        size += list.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size || index < 0) {
            rangeCheck(index);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            rangeCheck(index);
        }
        elementData[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index >= size || index < 0) {
            rangeCheck(index);
        }
        T removedElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elementData[i] == null) {
                    remove(i);
                    return element;
                }
            } else if (element.equals(elementData[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Such element doesn't exist in this arrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
            Object[] newArray = new Object[elementData.length + (elementData.length / 2)];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;

    }

    private void rangeCheck(int index) {
            throw new ArrayListIndexOutOfBoundsException("Such index " + index + " doesn't exist");
    }
}
