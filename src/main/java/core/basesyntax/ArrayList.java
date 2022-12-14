package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;
    private static final Object[] EMPTY_ELEMENTDATA = {};

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != EMPTY_ELEMENTDATA) {
            int newCapacity = (int) (oldCapacity * 1.5);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    public ArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        final int s;
        if ((s = size) == this.elementData.length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = value;
        size = s + 1;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] tempArray = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tempArray[i] = list.get(i);
        }
        int numNew = tempArray.length;
        if (numNew == 0) {
            return;
        }
        final int s;
        if (numNew > (elementData.length - (s = size))) {
            elementData = grow(s + numNew);
        }
        System.arraycopy(tempArray, 0, elementData, s, numNew);
        size = s + numNew;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final Object[] es = elementData;
        @SuppressWarnings("unchecked") T oldValue = (T) es[index];
        fastRemove(es, index);
        return oldValue;
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }

    @Override
    public T remove(T element) {
        final Object[] es = elementData;
        final int size = this.size;
        int i = 0;
        boolean flag = false;
        found:
        {
            if (element == null) {
                for (; i < size; i++)
                    if (es[i] == null) {
                        flag = true;
                        break found;
                    }
            } else {
                for (; i < size; i++) {
                    if (element.equals(es[i])) {
                        flag = true;
                        break found;
                    }
                }
            }
        }

        @SuppressWarnings("unchecked") T oldValue = (T) es[i];
        if (!flag) {
            throw new NoSuchElementException();
        }
        fastRemove(es, i);
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
}
