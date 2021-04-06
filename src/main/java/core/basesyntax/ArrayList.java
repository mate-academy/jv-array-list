package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String OUT_OF_BOUNDS_MESSAGE = "Array index is out of bounds";
    private static final String NO_SUCH_ELEMENT_MESSAGE = "No such element exists";
    private static final int DEFAULT_CAPACITY = 10;
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
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, true);
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        final Object[] tempArray = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tempArray[i] = list.get(i);
        }
        if (tempArray.length > (elementData.length - size)) {
            growBy(tempArray.length);
        }
        System.arraycopy(tempArray, 0, elementData, size, tempArray.length);
        size += tempArray.length;
    }

    @Override
    public T get(int index) {
        checkIndex(index, false);
        return elementData(index);
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, false);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);
        return removeElement(index);
    }

    @Override
    public T remove(T element) {
        int index;
        if ((index = getIndexOfElement(element)) == -1) {
            throw new NoSuchElementException(NO_SUCH_ELEMENT_MESSAGE);
        }
        return removeElement(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private T elementData(int index) {
        return (T) elementData[index];
    }

    private void grow() {
        growBy(0);
    }

    private void growBy(int size) {
        final int defaultCapacity = elementData.length + elementData.length / 2;
        final int newCapacity = elementData.length + size;
        final Object[] oldData = elementData;
        elementData = new Object[Math.max(defaultCapacity, newCapacity)];
        System.arraycopy(oldData, 0, elementData, 0, this.size);
    }

    private T removeElement(int index) {
        final int newSize = size - 1;
        @SuppressWarnings("unchecked")
        final T oldElement = (T) elementData[index];
        if (newSize > index) {
            System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
        }
        elementData[newSize] = null;
        size = newSize;
        return oldElement;
    }

    private void checkIndex(int index, boolean alternative) {
        if (alternative) {
            if (index < 0 || index > size) {
                throw new ArrayListIndexOutOfBoundsException(OUT_OF_BOUNDS_MESSAGE);
            }
        } else if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(OUT_OF_BOUNDS_MESSAGE);
        }
    }

    private int getIndexOfElement(T element) {
        for (int i = 0; i < size; i++) {
            T arrayElement = elementData(i);
            if (safeObjCompare(element, arrayElement)) {
                return i;
            }
        }
        return -1;
    }

    private boolean safeObjCompare(T a, T b) {
        return a == null ? b == null : a.equals(b);
    }
}
