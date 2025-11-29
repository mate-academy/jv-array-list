package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_SHIFT = 1;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;

    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal capacity. "
                    + "Please provide '0' or positive number instead: " + initialCapacity);
        }
    }

    private void setElementData(Object[] elementData) {
        this.elementData = elementData;
    }

    @Override
    public void add(T value) {
        ensureCapacity(1, size);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        ensureCapacity(1, size);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.isEmpty()) {
            return;
        }

        int additional = list.size();
        ensureCapacity(additional, size);
        for (int i = 0; i < additional; i++) {
            T value = list.get(i);
            elementData[size + i] = value;
        }
        size += additional;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        validateIndex(index, size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index, size);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index, size);
        @SuppressWarnings("unchecked")
        final T removed = (T) elementData[index];
        int moveCount = size - index - 1;
        if (moveCount > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, moveCount);
        }
        elementData[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("Element to remove not found: " + element);
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int reqCapacity, int size) {
        int minCapacity = reqCapacity + size;
        if (minCapacity > elementData.length
                && !(elementData == EMPTY_ELEMENT_DATA
                && minCapacity <= DEFAULT_CAPACITY)) {
            growCapacity(minCapacity);
        }
    }

    private void growCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != EMPTY_ELEMENT_DATA) {
            int newCapacity = newLength(oldCapacity,
                    minCapacity - oldCapacity,
                    oldCapacity >> GROWTH_SHIFT);
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            setElementData(newElementData);
        } else {
            int initialCapacity = (minCapacity > DEFAULT_CAPACITY) ? minCapacity : DEFAULT_CAPACITY;
            setElementData(new Object[initialCapacity]);
        }
    }

    private int newLength(int oldLength, int minGrowth, int prefGrowth) {
        int growth = (prefGrowth > minGrowth) ? prefGrowth : minGrowth;
        int prefLength = oldLength + growth;
        if (0 < prefLength && prefLength <= SOFT_MAX_ARRAY_LENGTH) {
            return prefLength;
        } else {
            // put code cold in a separate method
            return hugeLength(oldLength, minGrowth);
        }
    }

    private int hugeLength(int oldLength, int minGrowth) {
        int minLength = oldLength + minGrowth;
        if (minLength < 0) { // overflow
            throw new OutOfMemoryError(
                    "Required array length " + oldLength + " + " + minGrowth + " is too large");
        } else {
            return (minLength > SOFT_MAX_ARRAY_LENGTH) ? minLength : SOFT_MAX_ARRAY_LENGTH;
        }
    }

    private void validateIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: "
                    + index + ", size: " + size);
        }
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds for add: "
                    + index + ", size: " + size);
        }
    }

    public int indexOf(T element) {
        return indexOfRange(element, size);
    }

    private int indexOfRange(T element, int end) {
        if (element == null) {
            for (int i = 0; i < end; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < end; i++) {
                if (element.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
