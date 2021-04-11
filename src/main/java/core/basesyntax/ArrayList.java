package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private Object[] elementData = new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        add(value, elementData, size);
    }

    private void add(T value, Object[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = value;
        size = s + 1;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = value;
        size = s + 1;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] arrayForAdd = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arrayForAdd[i] = list.get(i);
        }
        int addLength = arrayForAdd.length;
        Object[] elementData;
        final int s;
        if (addLength > (elementData = this.elementData).length - (s = size)) {
            elementData = grow(s + addLength);
        }
        System.arraycopy(arrayForAdd, 0, elementData, s, addLength);
        size = s + addLength;
    }

    T elementData(int index) {
        return (T) elementData[index];
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData,
                newCapacity(minCapacity));
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            }
            if (minCapacity < 0) {
                throw new OutOfMemoryError();
            }
            return minCapacity;
        }
        return (newCapacity - MAX_ARRAY_SIZE <= 0)
                ? newCapacity
                : hugeCapacity(minCapacity);
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Negative index");
        }
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Negative index");
        }
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        final Object[] es = elementData;
        @SuppressWarnings("unchecked") T oldValue = (T) es[index];
        removeElement(es, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final Object[] es = elementData;
        final int size = this.size;
        int i = 0;
        boolean isFound = false;
        if (element == null) {
            for (; i < size; i++) {
                if (es[i] == null) {
                    isFound = true;
                    break;
                }
            }
        } else {
            for (; i < size; i++) {
                if (element.equals(es[i])) {
                    isFound = true;
                    break;
                }
            }
        }
        if (isFound) {
            removeElement(es, i);
            return element;
        } else {
            throw new NoSuchElementException("Element doesn`t exist");
        }
    }

    private void removeElement(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public int size() {
        return size;
    }
}
