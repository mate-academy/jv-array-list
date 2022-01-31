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
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        size++;
        checkIndex(index);
        if (size - elementData.length == 0) {
            elementData = grow(size + 1);
        }
        if (index < size) {
            if (index != size - 1) {
                System.arraycopy(elementData, index, elementData, index + 1, size);
            }
            elementData[index] = value;
        }

    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > size) {
            elementData = grow(list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    private Object[] grow(int minCapacity) {
        int newCapacity = elementData.length + (elementData.length >> 1);
        Object[] tempData = new Object[Math.max(newCapacity, minCapacity)];
        System.arraycopy(elementData, 0, tempData, 0, size);
        return tempData;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData(index);
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        if (size - index == 1) {
            elementData = grow(size + 1);
        }
        T removedObject = elementData(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (isEquals(elementData[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element [" + element + "] in list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T elementData(int index) {
        return (T) elementData[index];
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index [" + index + "] "
                    + "is out of bound for array size " + size);
        }
    }

    private boolean isEquals(Object o, T t) {
        return o == t || o != null && o.equals(t);
    }
}
