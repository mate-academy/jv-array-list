package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DIVIDER = 1;
    private T[] elementData;
    private int size;

//    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkLength();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        checkLength();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] newArray = (T[]) list.toArray();
        int addLength = newArray.length;
        checkLengthForAddAll(addLength);
        System.arraycopy(newArray, 0, this.elementData, size, addLength);
        size = size + addLength;
    }
    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
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
        final int index = findElement(element);
        T oldValue = (T) elementData[index];
        fastRemove(index);
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    private T[] grow(int minCapacity) {
        return Arrays.copyOf(elementData, newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> DIVIDER);
        if (newCapacity - minCapacity <= 0) {
            if (Arrays.equals(elementData, new Object[]{})) {
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            }
            if (minCapacity < 0) {
                throw new OutOfMemoryError();
            }
            return minCapacity;
        }
        return (newCapacity - Integer.MAX_VALUE <= 0)
                ? newCapacity : Integer.MAX_VALUE;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + ", Size: " + size);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + " is invalid");
        }
    }

    private void fastRemove(int index) {
        final int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(elementData, index + 1,
                    elementData, index, newSize - index);
        }
        elementData[size = newSize] = null;
    }

    private int findElement(T object) {
        for (int i = 0; i < elementData.length; i++) {
            if (object == elementData[i] || object != null && object.equals(elementData[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("Element: " + object + " is not found");
    }

    private void checkLength() {
        if (size == elementData.length) {
            elementData = grow(size + 1);
        }
    }

    private void checkLengthForAddAll(int length) {
        if (length == 0) {
            return;
        }
        if (length > (this.elementData.length - size)) {
            this.elementData = grow(size + length);
        }
    }
}
