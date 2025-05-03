package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private static final int startSize = 0;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        size = startSize;
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private void ensureExplicitCapacity(int minCapacity) {
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    private void arrayListIndexOutOfBounds(int index, boolean mode) {
        if (mode) {
            if (index > size || index < 0) {
                throw new ArrayListIndexOutOfBoundsException("Element not found in the list");
            }
        } else {
            if (index >= size || index < 0) {
                throw new ArrayListIndexOutOfBoundsException("Element not found in the list");
            }
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        Object[] copy = new Object[newCapacity];
        System.arraycopy(elementData, 0, copy, 0,
                Math.min(elementData.length, newCapacity));
        elementData = copy;
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        }
        elementData[--size] = null;
    }

    @Override
    public void add(T value) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        arrayListIndexOutOfBounds(index, true);
        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] a = new Object[list.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = list.get(i);
        }
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
    }

    @Override
    public T get(int index) {
        arrayListIndexOutOfBounds(index, false);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        arrayListIndexOutOfBounds(index, false);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        arrayListIndexOutOfBounds(index, false);
        Object oldValue = elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        }
        elementData[--size] = null; // clear to let GC do its work
        return (T) oldValue;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int index = 0; index < size; index++) {
                if (elementData[index] == null) {
                    fastRemove(index);
                    return element;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (element.equals(elementData[index])) {
                    fastRemove(index);
                    return element;
                }
            }
        }
        throw new NoSuchElementException("Illegal value");
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
