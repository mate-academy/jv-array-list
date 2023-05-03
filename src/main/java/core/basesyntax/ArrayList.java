package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int NOT_EXISTING_INDEX = -1;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = new Object[]{};
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addAll(List<T> list) {
        Object[] objectsList = toArray(list);
        for (Object element : objectsList) {
            add((T) element);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndex(index, size);
        return (T) elementData[index];
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
        @SuppressWarnings("unchecked")
        T oldValue = (T) elementData[index];
        fastRemove(index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int searchingIndex = foundElementIndex(element);
        if (searchingIndex > NOT_EXISTING_INDEX) {
            fastRemove(searchingIndex);
            return element;
        }
        throw new NoSuchElementException("Not found such element.");
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
        checkIndex(index, size + 1);
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = value;
        size++;
    }

    private int foundElementIndex(T element) {
        final Object[] elements = elementData;
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

    private void fastRemove(int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(elementData, i + 1,
                    elementData, i, newSize - i);
        }
        elementData[size = newSize] = null;
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
            return Math.max(minLength,
                    SOFT_MAX_ARRAY_LENGTH);
        }
    }

    private void grow() {
        int minCapacity = size + 1;
        int oldCapacity = elementData.length;
        elementData =
                (oldCapacity > 0
                        || !Arrays.equals(elementData, DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA))
                        ? Arrays.copyOf(elementData, newLength(oldCapacity,
                        minCapacity - oldCapacity,
                        oldCapacity >> 1))
                        : new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
    }

    private void checkIndex(int index, int length) {
        if (index < 0 || index >= length) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound exception, "
                    + "total length is: [" + length + "], when index is : ["
                    + index + "].");
        }
    }
}
