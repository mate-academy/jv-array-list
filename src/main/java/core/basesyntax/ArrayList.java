package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = oldCapacity + Math.max(minCapacity - oldCapacity, oldCapacity >> 1);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void fastRemove(Object[] tmpList, int index) {
        final int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(tmpList, index + 1, tmpList, index, newSize - index);
        }
        size = newSize;
        tmpList[size] = null;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow(size + 1);
        }
        elementData[size] = value;
        size += 1;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        Object[] elementData;
        if (size == (elementData = this.elementData).length) {
            elementData = grow(size + 1);
        }
        System.arraycopy(elementData, index, elementData, index + 1,size - index);
        elementData[index] = value;
        size += 1;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] tmpList = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tmpList[i] = list.get(i);
        }
        int numNew = tmpList.length;
        if (numNew != 0) {
            Object[] elementData;
            if (numNew > (elementData = this.elementData).length - size) {
                elementData = grow(size + numNew);
            }
            System.arraycopy(tmpList, 0, elementData, size, numNew);
            size += numNew;
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
        final Object[] tmpArray = elementData;
        T oldValue = (T) tmpArray[index];
        fastRemove(tmpArray, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        Object[] tmpList = elementData;
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(element, elementData[i])) {
                fastRemove(tmpList, i);
                index = i;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("No such element");
        } else {
            return (T) elementData[index];
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
