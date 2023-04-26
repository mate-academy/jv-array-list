package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY;
    private static final int SOFT_MAX_ARRAY_LENGTH;
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    private static final int NOT_EXISTING_INDEX;

    static {
        NOT_EXISTING_INDEX = -1;
        DEFAULT_CAPACITY = 10;
        SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
        DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = new Object[]{};
    }

    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
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
        int searchingIndex = foundElementIndex(element);
        if (searchingIndex > NOT_EXISTING_INDEX) {
            fastRemove(elementData, searchingIndex);
            return element;
        }
        throw new NoSuchElementException("Not found such element.");
    }

    private int foundElementIndex(T element) {
        final Object[] elements = elementData;
        final int size = this.size;
        int i = 0;
        if (element == null) {
            for (; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return NOT_EXISTING_INDEX;
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
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

    private void add(T element, Object[] elementData, int size) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = element;
        this.size = size + 1;
    }

    private static int newLength(int oldLength, int minGrowth, int prefGrowth) {

        int prefLength = oldLength + Math.max(minGrowth, prefGrowth);
        if (0 < prefLength && prefLength <= SOFT_MAX_ARRAY_LENGTH) {
            return prefLength;
        } else {
            return hugeLength(oldLength, minGrowth);
        }
    }

    private static int hugeLength(int oldLength, int minGrowth) {
        int minLength = oldLength + minGrowth;
        if (minLength < 0) {
            throw new OutOfMemoryError(
                    "Required array length " + oldLength + " + " + minGrowth + " is too large");
        } else {
            return Math.max(minLength, SOFT_MAX_ARRAY_LENGTH);
        }
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = newLength(oldCapacity,
                    minCapacity - oldCapacity,
                    oldCapacity >> 1);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    @SuppressWarnings("unchecked")
    private T elementData(int index) {
        return (T) elementData[index];
    }

    private void checkIndex(int index, int length) {
        if (index < 0 || index >= length) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound exception, "
                    + "total length is: [" + length + "], when index is : ["
                    + index + "].");
        }
    }
}
