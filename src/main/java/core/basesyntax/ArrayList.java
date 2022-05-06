package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int STORAGE_DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTSDATA = {};
    private Object[] elementsData;
    private int size;

    public ArrayList() {
        this.elementsData = new Object[STORAGE_DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementsData.length) {
            elementsData = grow();
        }
        elementsData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index entered.");
        }
        if (size == elementsData.length || size + 1 == elementsData.length) {
            grow();
        }
        if (index < size) {
            System.arraycopy(elementsData, index, elementsData, index + 1, size + 1);
        }
        elementsData[index] = value;
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
        indexCheck(index);
        return (T) elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        final T removedElement = (T) elementsData[index];
        elementsData[index] = null;
        if (index != size - 1) {
            arrayOffset(index);
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementsData[i] == element
                    || elementsData[i] != null
                    && elementsData[i].equals(element)) {
                elementsData[i] = null;
                arrayOffset(i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("No such element in ArrayList.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        return elementsData = Arrays.copyOf(elementsData, newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = elementsData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (elementsData == DEFAULTCAPACITY_EMPTY_ELEMENTSDATA) {
                return Math.max(STORAGE_DEFAULT_CAPACITY, minCapacity);
            }
            if (minCapacity < 0) {
                throw new OutOfMemoryError();
            }
            return minCapacity;
        }
        return newCapacity;
    }

    private void arrayOffset(int index) {
        if (index == 0) {
            System.arraycopy(elementsData, index + 1, elementsData, index, size - 1);
        } else {
            System.arraycopy(elementsData, index, elementsData, index - 1, size - 1);
        }
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index entered.");
        }
    }
}

