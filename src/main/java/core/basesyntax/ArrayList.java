package core.basesyntax;

import java.util.Collection;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        setElementData(new Object[DEFAULT_CAPACITY]);
    }

    public ArrayList(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        elementData = c.toArray();
        setSize(elementData.length);
        if (size == 0) {
            setElementData(new Object[DEFAULT_CAPACITY]);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Object[] getElementData() {
        return elementData;
    }

    public void setElementData(Object[] elementData) {
        this.elementData = elementData;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        ensureCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("List cannot be null");
        }
        int newSize = size + list.size();
        ensureCapacity(newSize);
        for (int i = 0; i < list.size(); i++) {
            elementData[size + i] = list.get(i);
        }
        size = newSize;
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
        T removedElement = (T) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
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
            growCapacity();
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            growCapacity(minCapacity);
        }
    }

    private void growCapacity() {
        int newCapacity = elementData.length + (elementData.length >> 1);
        elementData = copyOf(elementData, newCapacity);
    }

    private void growCapacity(int minCapacity) {
        int newCapacity = Math.max(elementData.length + (elementData.length >> 1), minCapacity);
        elementData = copyOf(elementData, newCapacity);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private Object[] copyOf(Object[] original, int newLength) {
        Object[] newArray = new Object[newLength];
        for (int i = 0; i < original.length && i < newLength; i++) {
            newArray[i] = original[i];
        }
        return newArray;
    }
}
