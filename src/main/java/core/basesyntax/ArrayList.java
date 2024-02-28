package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_FACTOR = 1.5;
    private int size;
    private Object[] elementData = new Object[size];

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity >= DEFAULT_CAPACITY) {
            int newCapacity = (int) (oldCapacity * INCREASE_FACTOR);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private Object[] add(T e, Object[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow(size + 1);
        }
        elementData[size++] = e;
        return elementData;
    }

    @Override
    public void add(T value) {
        add(value, elementData, size);
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("there is no such index");
        }

        Object[] elementData;
        if (size == (elementData = this.elementData).length) {
            elementData = grow(size + 1);
        }

        System.arraycopy(elementData, index, elementData, index + 1, size - index);

        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        rangeCheckForAdd(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForAdd(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForAdd(index);
        final Object[] es = elementData;

        @SuppressWarnings("unchecked") T oldValue = (T) es[index];
        fastRemove(es, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final Object[] es = elementData;
        int index = -1;
        for (int i = 0; i < es.length; i++) {
            if ((es[i] == null && element == null) || (es[i] != null && es[i].equals(element))) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            return remove(index);
        } else {
            throw new NoSuchElementException("Element not found in the array");
        }
    }

    private void fastRemove(Object[] es, int i) {
        rangeCheckForAdd(i);
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
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

    private void rangeCheckForAdd(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elementData, size));
    }
}
