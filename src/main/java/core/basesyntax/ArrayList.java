package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        Object[] oldElement = elementData;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int prefLength = oldCapacity + Math.max(minCapacity - oldCapacity, oldCapacity >> 1);
            elementData = new Object[prefLength];
            System.arraycopy(oldElement, 0, elementData, 0, oldCapacity);
            return elementData;
        } else {
            elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
            return elementData;
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("this index is out of bound " + index);
        }
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("this index is out of bound " + index);
        }
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow(size + 1);
        }
        elementData[size] = value;
        size = size + 1;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (size == elementData.length) {
            elementData = grow(size + 1);
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = (T) value;
        size = size + 1;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] a = new Object[list.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = list.get(i);
        }
        int numNew = a.length;
        if (numNew == 0) {
            return;
        }
        Object[] elementData = this.elementData;
        final int s = size;
        if (numNew > elementData.length - s) {
            elementData = grow(s + numNew);
        }
        System.arraycopy(a, 0, elementData, s, numNew);
        size = s + numNew;
        this.elementData = elementData;
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementData[index] = value;
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        final Object[] es = elementData;
        T oldValue = (T) es[index];
        fastRemove(es, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        T oldValue = element;
        for (int j = 0; j < size; j++) {
            if (element == null && elementData[j] == null
                    || (element != null && element.equals(elementData[j]))) {
                fastRemove(elementData, j);
                return oldValue;
            }
        }
        throw new NoSuchElementException("Such element hasn't been found " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
