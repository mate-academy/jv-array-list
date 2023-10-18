package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int capacity = 10;
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        elementData = new Object[capacity];
    }

    private void grow() {
        int oldCapacity = capacity;
        capacity = capacity + (capacity >> 1);
        Object[] tempArray = new Object[capacity];
        System.arraycopy(elementData, 0, tempArray, 0, oldCapacity);
        elementData = tempArray;
    }

    private void grow(int addedSize) {
        while (addedSize > elementData.length - size) {
            grow();
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("There is no element with this index");
        }
    }

    private void checkAddIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index is invalid");
        }
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        if (size == capacity) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > elementData.length - size) {
            grow(list.size());
        }
        for (int i = 0; i < list.size(); i++, size++) {
            elementData[size] = list.get(i);
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
        final Object oldElement = elementData[index];
        int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
        }
        size = newSize;
        elementData[size] = null;
        return (T) oldElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == null ? element == null : elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in the list");
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
