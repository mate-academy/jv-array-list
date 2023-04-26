package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE;
    private static final int MIN_SIZE = 0;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = MIN_SIZE;
    }

    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - MAX_CAPACITY > 0) {
            newCapacity = MAX_CAPACITY;
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private void checkRangeForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index: " + index);
        }
    }

    private void checkRange(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index: " + index);
        }
    }

    private T fastRemove(int index) {
        T value = (T) elementData[index];
        if (size - 1 > index) {
            System.arraycopy(elementData, index + 1, elementData, index, size - 1);
        }
        size--;
        return value;
    }

    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public void add(T value) {
        growIfNeeded();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkRangeForAdd(index);
        growIfNeeded();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] newArray = list.toArray();
        int newArrayLength = newArray.length;
        if (newArrayLength > elementData.length - size) {
            grow();
        }
        System.arraycopy(newArray, 0, elementData, size, newArrayLength);
        size = size + newArrayLength;
    }

    @Override
    public T get(int index) {
        checkRange(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkRange(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        return fastRemove(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i <= size; i++) {
            if (elementsEqual(elementData[i], element)) {
                return fastRemove(i);
            }
        }
        throw new NoSuchElementException(element.toString() + ": doesn't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean elementsEqual(Object first, Object second) {
        return (first == second) || (first != null && first.equals(second));
    }

    private void growIfNeeded() {
        if (size == elementData.length) {
            grow();
        }
    }
}
