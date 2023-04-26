package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENT_DATA = {};
    public static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = DEFAULTCAPACITY_EMPTY_ELEMENT_DATA;
    }

    @Override
    public void add(T value) {
        add(value, elementData, size);
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size + 1);
        final int size;
        Object[] elementData;
        if ((size = this.size) == (elementData = this.elementData).length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = value;
        this.size = size + 1;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] objectsList = toArray(list);
        int numNew = objectsList.length;
        if (numNew == 0) {
            return;
        }
        Object[] elementData;
        final int size;
        if (numNew > (elementData = this.elementData).length - (size = this.size)) {
            elementData = grow(size + numNew);
        }
        System.arraycopy(objectsList, 0, elementData, size, numNew);
        this.size = size + numNew;
    }

    @Override
    public T get(int index) {
        checkIndex(index, size);
        return elementData(index);
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        final Object[] elements = elementData;
        @SuppressWarnings("unchecked")
        T oldValue = (T) elements[index];
        fastRemove(elements, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final Object[] es = elementData;
        final int size = this.size;
        int i = 0;
        boolean isFound = false;
        found:
        {
            if (element == null) {
                for (; i < size; i++)
                    if (es[i] == null) {
                        isFound = true;
                        break found;
                    }
            } else {
                for (; i < size; i++)
                    if (element.equals(es[i])) {
                        isFound = true;
                        break found;
                    }
            }
        }
        if (isFound) {
            fastRemove(es, i);
            return element;
        } else {
            throw new NoSuchElementException("Not found such element.");
        }

    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray(List<T> list) {
        Object[] objects = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            objects[i] = list.get(i);
        }
        return objects;
    }

    private void add(T element, Object[] elementData, int size) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = element;
        this.size = size + 1;
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    public static int newLength(int oldLength, int minGrowth, int prefGrowth) {
        // preconditions not checked because of inlining
        // assert oldLength >= 0
        // assert minGrowth > 0

        int prefLength = oldLength + Math.max(minGrowth, prefGrowth); // might overflow
        if (0 < prefLength && prefLength <= SOFT_MAX_ARRAY_LENGTH) {
            return prefLength;
        } else {
            // put code cold in a separate method
            return hugeLength(oldLength, minGrowth);
        }
    }

    private static int hugeLength(int oldLength, int minGrowth) {
        int minLength = oldLength + minGrowth;
        if (minLength < 0) { // overflow
            throw new OutOfMemoryError(
                    "Required array length " + oldLength + " + " + minGrowth + " is too large");
        } else return Math.max(minLength, SOFT_MAX_ARRAY_LENGTH);
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    @SuppressWarnings("unchecked")
    private T elementData(int index) {
        return (T) elementData[index];
    }

    private void checkIndex(int index, int length) {
        if (index < 0 || index >= length)
            throw new ArrayListIndexOutOfBoundsException("Index out of bound exception, "
                    + "total length is: [" + length + "], when index is : ["
                    + index + "].");
    }
}
