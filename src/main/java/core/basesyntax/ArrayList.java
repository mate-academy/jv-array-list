package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INDEX_NOT_FOUND = -1;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size + 1 <= elementData.length) {
            elementData[size++] = value;
            return;
        }
        grow();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (index == size) {
            add(value);
            return;
        }
        if (size + 1 > elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size++ - index);
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index > INDEX_NOT_FOUND) {
            return remove(index);
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

    private void grow() {
        Object[] curElements = elementData;
        elementData = new Object[size + (size >> 1)];
        System.arraycopy(curElements, 0, elementData, 0, size);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Array index %d out of bounds", index)
            );
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d not suitable for add", index)
            );
        }
    }

    private int indexOf(Object obj) {
        for (int i = 0; i < size; i++) {
            if (obj == elementData[i] || (obj != null && obj.equals(elementData[i]))) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }
}
