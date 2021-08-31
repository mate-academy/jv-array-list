package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private Object [] elementData;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity"
                    + initialCapacity);
        }
    }

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private Object[] grow(int minCapacity) {
        Object[] biggerArray = new Object[(int)(minCapacity * 1.5)];
        for (int i = 0; i < elementData.length; i++) {
            biggerArray[i] = elementData[i];
        }
        return biggerArray;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        int potentialSize = size + 1;
        if (potentialSize > elementData.length) {
            elementData = grow(elementData.length);
        }
        indexInBoundsCheck(index, potentialSize);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) {
        if ((elementData.length - size) <= list.size()) {
            grow(elementData.length);
        }
        int j = 0;
        for (int i = size; j < list.size(); i++) {
            elementData[i] = list.get(j);
            j++;
            size++;
        }
    }

    @Override
    public T get(int index) {
        indexInBoundsCheck(index,size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexInBoundsCheck(index,size);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexInBoundsCheck(index, size);
        Object oldObject = elementData[index];
        size = size - 1;
        System.arraycopy(elementData, index + 1, elementData, index,size - index);
        return (T) oldObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element || elementData[i] != null
                    && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in list, check again");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void indexInBoundsCheck(int index, int bounds) {
        if (index < 0 || index >= bounds || bounds < 0) {
            throw new ArrayListIndexOutOfBoundsException("Unreacheble index, please check again");
        }
    }
}
