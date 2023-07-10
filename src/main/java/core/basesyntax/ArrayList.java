package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size + 1) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + "is out of bounds " + size);
        }
        if (size == elementData.length || index > size) {
            elementData = grow();
        }
        T[] elementsAfterIndex =(T[]) new Object[size - index + 1];
        System.arraycopy(elementData,index,elementsAfterIndex,0,size - index + 1);
        elementData[index] = value;
        if (index <= size) {
            System.arraycopy(elementsAfterIndex, 0, elementData, index + 1, elementsAfterIndex.length - 1);
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T elem = (T) list.get(i);
            add(elem);
        }
    }

    private T[] grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        T[] newElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData,0, newElementData,0, oldCapacity);
        return newElementData;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + "is out of bounds " + size);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + "is out of bounds " + size);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + "is out of bounds " + size);
        }
        T value = (T) elementData[index];
        if (index < size - 1) {
            System.arraycopy(elementData, index + 1, elementData, index, size - 1);
        }
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if ((elementData[i] != null && elementData[i].equals(element))
                    || (elementData[i] == null && element == null)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element was not founded");
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
