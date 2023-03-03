package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        lengthChange();
        elementData[size] = value;
        size = size + 1;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index " + index
                    + " out of list size " + size);
        }
        if (index == size) {
            add(value);
            return;
        }
        lengthChange();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size = size + 1;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        arrayRangeCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        arrayRangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        arrayRangeCheck(index);
        T removeT = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index,
                elementData.length - index - 1);
        size = size - 1;
        return removeT;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] != null && elementData[i].equals(element)
                    || elementData[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("element " + element + " not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void arrayRangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index " + index
                    + " out of list size " + size);
        }
    }

    private void lengthChange() {
        if (size == elementData.length) {
            Object[] newElementData = new Object[elementData.length + elementData.length / 2];
            System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
            elementData = newElementData;
        }
    }
}
