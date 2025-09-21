package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            elementData = new Object[0];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        ensureCapacity();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        if (listSize == 0) {
            return;
        }
        ensureCapacity(size + listSize);
        for (int i = 0; i < listSize; i++) {
            elementData[size++] = list.get(i);
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
        T oldValue = (T) elementData[index];
        fastRemove(index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        T oldValue;
        for (int i = 0; i < size; i++) {
            Object currentElement = elementData[i];
            if ((element == null && currentElement == null)
                    || (element != null && element.equals(currentElement))) {
                oldValue = (T) currentElement;
                fastRemove(i);
                return oldValue;
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        int oldCapacity = elementData.length;
        if (oldCapacity == 0) {
            elementData = new Object[DEFAULT_CAPACITY];
        } else {
            int newCapacity = newCapacity();
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, oldCapacity);
            elementData = newElementData;
        }
    }

    private int newCapacity() {
        return elementData.length
                + Math.max(1, (int)(elementData.length * (GROWTH_FACTOR - 1)));
    }

    private void ensureCapacity() {
        if (size == elementData.length) {
            growIfArrayFull();
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int newCapacity = Math.max(newCapacity(), minCapacity);
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void fastRemove(int index) {
        final int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
        }
        size = newSize;
        elementData[size] = null;
    }
}
