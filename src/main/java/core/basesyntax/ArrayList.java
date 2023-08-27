package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if ((oldCapacity > 0) || (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            Object[] elementDataWithNewCapacity = new Object[newCapacity];
            System.arraycopy(elementData, 0, elementDataWithNewCapacity,0, size);
            return elementData = elementDataWithNewCapacity;
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private void checkIndexRange(int index, int size) {
        if ((index < 0) || (index > size)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private int findRemovedElementIndex(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == elementData[i])
                    || ((element != null)
                    && (element.equals(elementData[i])))) {
                return i;
            }
        }
        throw new NoSuchElementException("No such element in ArrayList");
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
        checkIndexRange(index, size);
        if (size == elementData.length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
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
        checkIndexRange(index, size - 1);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexRange(index, size - 1);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexRange(index, size - 1);
        T removedElement = (T) elementData[index];
        if (index != (size - 1)) {
            System.arraycopy(elementData, index + 1,
                    elementData, index,
                    size - index);
        }
        elementData[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = findRemovedElementIndex(element);
        return remove(index);

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
