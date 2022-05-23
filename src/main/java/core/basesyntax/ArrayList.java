package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULT_ELEMENTS = {};
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    T elements(int index) {
        return (T) elements[index];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size);
        int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elements).length) {
            elementData = grow(size + 1);
        }
        if (index == 0) {
            System.arraycopy(elementData, 0, elementData, 1, s);
        } else {
            System.arraycopy(elementData, 0, elementData, 0, index);
            System.arraycopy(elementData, index, elementData, index + 1, s - index);
        }
        elementData[index] = value;
        size = s + 1;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < ((ArrayList<T>) list).size; i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, (size == 0) ? 0 : (size - 1));
        return elements(index);
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, (size == 0) ? 0 : (size - 1));
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, (size == 0) ? 0 : (size - 1));
        final Object[] es = elements;

        @SuppressWarnings("unchecked") T oldValue = (T) es[index];
        final int newSize;
        if ((newSize = size - 1) > index)
            System.arraycopy(es, index + 1, es, index, newSize - index);
        es[size = newSize] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        Object[] es = elements;

        for (int i = 0; i < this.size; i++) {

            if (equal(element, (T) es[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("There is no such element.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elements.length;
        if (oldCapacity > 0 || elements != DEFAULT_ELEMENTS) {
            int newCapacity = newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elements = Arrays.copyOf(elements, newCapacity);
        } else {
            return elements = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private int newLength(int oldLength, int minGrowth, int prefGrowth) {
        int prefLength = oldLength + Math.max(minGrowth, prefGrowth);
        if (0 < prefLength && prefLength <= SOFT_MAX_ARRAY_LENGTH) {
            return prefLength;
        } else {
            return hugeLength(oldLength, minGrowth);
        }
    }

    private int hugeLength(int oldLength, int minGrowth) {
        int minLength = oldLength + minGrowth;
        if (minLength < 0) {
            throw new OutOfMemoryError(
                    "Required array length " + oldLength + " + "
                            + minGrowth + " is too large");
        } else {
            return Math.max(minLength, SOFT_MAX_ARRAY_LENGTH);
        }
    }

    private void checkIndex(int index, int size) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index passed to the method is negative or " +
                            "bigger than size of ArrayList. " +
                            "Index: " + index + ", Size: " + size);
        }
    }

    private boolean equal(T element1, T element2) {
        if (element1 == element2) {
            return true;
        }
        if (element2 == null || element1 == null) {
            return false;
        }
        return element1.equals(element2);
    }

}
