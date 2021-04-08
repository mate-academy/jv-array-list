package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_LIST = {};
    private Object[] elementArray;
    private int size;

    public ArrayList() {
        this.elementArray = EMPTY_LIST;
    }

    private Object[] grow() {
        int oldCapacity = elementArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1) + 1;
        if (oldCapacity > 0) {
            return elementArray = Arrays.copyOf(elementArray, newCapacity);
        }
        return elementArray = new Object[Math.max(DEFAULT_CAPACITY, newCapacity)];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        if (index == elementArray.length) {
            elementArray = grow();
        }
        if (index < size) {
            Object[] tempArray = new Object[size - index];
            System.arraycopy(elementArray, index, tempArray, 0, size - index);
            elementArray[index] = value;
            System.arraycopy(tempArray, 0, elementArray, index + 1, tempArray.length);
        } else {
            elementArray[index] = value;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elementArray[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementArray[index] = value;
    }

    @Override
    public T remove(T element) {
        if (getIndexOfElement(element) < 0) {
            throw new NoSuchElementException("There is no such element");
        }
        return remove(getIndexOfElement(element));
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        Object oldValue = elementArray[index];
        System.arraycopy(elementArray, index + 1, elementArray, index, size - index - 1);
        size--;
        return (T) oldValue;
    }

    public int getIndexOfElement(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementArray[i] || element != null && element.equals(elementArray[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index goes beyond array bounds");
        }
    }
}
