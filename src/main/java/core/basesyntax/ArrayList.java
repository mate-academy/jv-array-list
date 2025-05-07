package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROW_BY = 2;
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
        rangeCheckForAdd(index);
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndexInArray(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexInArray(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexInArray(index);
        T oldValue = get(index);
        removeElementByIndex(elementData, index);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index;
        if ((index = findByValue(element)) == -1) {
            throw new NoSuchElementException("Element not found: " + element);
        }
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

    private void grow() {
        Object[] newElementData = new Object[elementData.length * GROW_BY];
        System.arraycopy(elementData, 0, newElementData,0, elementData.length);
        elementData = newElementData;
    }

    private void removeElementByIndex(Object[] data, int index) {
        final int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(data, index + 1, data, index, newSize - index);
        }
        data[newSize] = null;
    }

    private void checkIndexInArray(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index);
        }
    }

    private int findByValue(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, elementData[i])) {
                return i;
            }
        }
        return -1;
    }
}
