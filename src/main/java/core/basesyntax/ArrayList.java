package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private transient Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    @Override
    public void add(T value) {
        add(value, elementData, size);
    }

    private void add(T e, Object[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = e;
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

        Object[] a = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            a[i] = list.get(i);
        }
        int numNew = a.length;
        if (numNew == 0) {
            return;
        }
        Object[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size)) {
            elementData = grow(s + numNew);
        }
        int numMoved = s - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, size - 1,
                    elementData, numNew,
                    numMoved);
        }
        System.arraycopy(a, 0, elementData, size, numNew);
        size = s + numNew;
    }

    @Override
    public T get(int index) {
        rangeCheckForAdd(index + 1);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForAdd(index + 1);

        //  T oldValue = elementData[index];
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForAdd(index + 1);
        final Object[] es = elementData;
        @SuppressWarnings("unchecked") T oldValue = (T) es[index];
        fastRemove(es, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        Object result;
        final int size = this.size;
        int i = 0;
        found:
        {
            if (element == null) {
                for (; i < size; i++) {
                    if (elementData[i] == null) {
                        break found;
                    }
                }
            } else {
                for (; i < size; i++) {
                    if (element.equals(elementData[i])) {
                        break found;
                    }
                }
            }
            throw new NoSuchElementException();
        }
        result = elementData[i];
        fastRemove(elementData, i);
        return (T) result;
    }

    private void fastRemove(Object[] es, int i) {
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

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int i = elementData.length * 2;
        Object[] newElementData = new Object[i];
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
            return elementData = newElementData;
            //= Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private void rangeCheckForAdd(int index) {
        if (index == size) {
            return;
        }
        if ((index > size - 1 && size != 0) || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index more then size");
        }
    }
}
