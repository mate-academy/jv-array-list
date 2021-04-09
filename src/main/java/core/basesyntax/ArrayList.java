package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float CAPACITY_MULTIPLIER = 1.5f;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private Object[] grow(int minCapacity) {
        T[] newElementData = (T[]) new Object[minCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
        return elementData;
    }

    public void ensureCapacity(int capacity) {
        while (capacity > elementData.length) {
            grow((int) (elementData.length * CAPACITY_MULTIPLIER));
        }
    }

    private void checkingIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index Out Of Bounds");
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index Out Of Bounds");
        }
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
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
        checkingIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkingIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkingIndex(index);
        T newElementData = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return newElementData;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == element) || (elementData[i] != null
                    && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
