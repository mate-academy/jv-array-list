package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        growIfArrayFull(size);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(" Index " + index + " out of bounds List");
        }
        growIfArrayFull(size);
        System.arraycopy(elementData,index,elementData,index + 1,size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] listInObjectArray = listToArray(list);
        growIfArrayFull(list.size() + size);
        System.arraycopy(listInObjectArray,0,elementData,size,listInObjectArray.length);
        size += listInObjectArray.length;
    }

    @Override
    public T get(int index) {
        rangeCheckForAdd(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForAdd(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForAdd(index);
        Object element = elementData[index];
        if (index == size - 1) {
            elementData[index] = null;
        } else {
            System.arraycopy(elementData, index + 1, elementData, index, size - index);
        }
        size--;
        return (T)element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null && element == null) {
                return remove(i);
            } else if (elementData[i] != null && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Not found element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull(int minSize) {
        if (minSize >= elementData.length) {
            Object[] copyElementData = elementData.clone();
            while (elementData.length <= minSize) {
                elementData = new Object[size + size / 2];
            }
            System.arraycopy(copyElementData, 0, elementData, 0, copyElementData.length);
        }
    }

    private Object[] listToArray(List<T> list) {
        Object[] listInArray = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listInArray[i] = list.get(i);
        }
        return listInArray;
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(" Index " + index + " out of bounds List");
        }
    }
}
