package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        this.elementData = new Object[]{};
    }

    public ArrayList(int customCapacity) {
        if (customCapacity > 0) {
            this.elementData = new Object[customCapacity];
        } else if (customCapacity == 0) {
            this.elementData = new Object[]{};
        } else {
            throw new RuntimeException("Impossible capacity: "
                    + customCapacity);
        }
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
        Object[] a = list.toArray();
        int addLength = a.length;
        checkLengthForAddAll(addLength);
        System.arraycopy(a, 0, this.elementData, size, addLength);
        size = size + addLength;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        rangeCheckForGet(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForGet(index);
        elementData[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        rangeCheckForGet(index);
        T oldValue = (T) elementData[index];
        fastRemove(index);
        return oldValue;
    }

    @SuppressWarnings("unchecked")
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

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (Arrays.equals(elementData, DEFAULTCAPACITY_EMPTY_ELEMENTDATA)) {
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
            throw new ArrayListIndexOutOfBoundsException("Can't add inappropriate index: "
                    + index);
        }
    }

    private void rangeCheckForGet(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't get inappropriate index: "
                    + index);
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

    private int findElement(T o) {
        for (int i = 0; i < elementData.length; i++) {
            if (o == elementData[i] || o != null && o.equals(elementData[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("Can't find element: " + o);
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
