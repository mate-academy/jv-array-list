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
        ensureCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of size list");
        }
        ensureCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            ensureCapacity();
            elementData[i] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of size list");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of size list");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of size list");
        }
        T oldValue = (T) elementData[index];
        int numMove = size - index - 1;
        if (numMove > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMove);
        }
        elementData[size] = null;
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == elementData.length) {
            grow();
        }
    }

    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] newElementData = new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }
}
