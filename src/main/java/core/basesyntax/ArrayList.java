package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private Object[] elementData;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Illegal Capacity: "
                    + initialCapacity);
        }
    }

    public ArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }

    @Override
    public void add(T value) {
        add(value, elementData, size);
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        final int s = size;
        Object[] elementDataCopy = this.elementData;

        if (size == elementData.length) {
            elementData = grow();
        }
        System.arraycopy(elementDataCopy, index,
                elementData, index + 1,
                s - index);

        elementData[index] = value;
        size = s + 1;
    }

    private void add(T e, Object[] elementData, int index) {
        if (index == elementData.length) {
            elementData = grow();
        }
        elementData[index] = e;
        size = size + 1;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        rangeCheckForGetRemove(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForGetRemove(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForGetRemove(index);
        final Object[] es = elementData;
        T oldValue = (T) es[index];
        fastRemove(es, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final Object[] es = elementData;
        final int size = this.size;
        int i = 0;
        found:
        {
            if (element == null) {
                for (; i < size; i++) {
                    if (es[i] == null) {
                        break found;
                    }
                }
            } else {
                for (; i < size; i++) {
                    if (element.equals(es[i])) {
                        break found;
                    }
                }
            }
            throw new NoSuchElementException("Can't remove element");
        }
        fastRemove(es, i);
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void fastRemove(Object[] es, int index) {
        rangeCheckForGetRemove(index);
        final int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(es, index + 1, es, index, newSize - index);
        }
        es[size = newSize] = null;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index:" + (index));
        }
    }

    private void rangeCheckForGetRemove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index:" + (index));
        }
    }

    private Object[] grow() {
        int oldCapacity = elementData.length;
        int newCapacity;
        if (elementData.length < DEFAULT_CAPACITY) {
            newCapacity = DEFAULT_CAPACITY;
        } else {
            newCapacity = oldCapacity + (oldCapacity >> 1);
        }
        return elementData = Arrays.copyOf(elementData,
                newCapacity);
    }
}
