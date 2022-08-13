package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        this.elements = (T[]) new Object[initialCapacity];
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    private void rangeCheckForGetSetRemove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    private void grow() {
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        T[] newElements = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements , 0, size);
        elements = newElements;
    }

    private T[] grow() {
        return grow(size);
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
        rangeCheckForAdd(index);
        if (size == elementData.length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
        if (size < elementData.length) {
            elementData = grow(size + 1);
        }
        System.arraycopy(elementData, 0, elementData, size + 1, size + 1);
    }

    @Override
    public T get(int index) {
        rangeCheckForGetSetRemove(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForGetSetRemove(index);
        T deleteElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        size = size - 1;
        return deleteElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == element) || (elementData[i] != null
                    && elementData[i].equals(element))) {
                T deleteElement = elementData[i];
                System.arraycopy(elementData, i + 1, elementData, i, size - 1 - i);
                size = size - 1;
                return deleteElement;
            }
        }
        throw new NoSuchElementException("Couldn't find element " + element);
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
