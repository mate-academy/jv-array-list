package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size + 1 > elementData.length) {
            ensureCapacity();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size || checkIndex(index)) {
            if (size + 1 > elementData.length) {
                ensureCapacity();
            }
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > elementData.length) {
            ensureCapacity();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index); // index >= 0,  index < size
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index); // index >= 0,  index < size
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index); // index >= 0  index < size
        int numMoved = size - index - 1;
        T removedValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("The element doesn't exist" + element);
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
        int newCapacity = (elementData.length * 3 / 2) + 1;
        T[] newElementData = (T[])new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    private boolean checkIndex(int index) {
        if (index < size && index >= 0) {
            return true;
        }
        throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
    }
}
