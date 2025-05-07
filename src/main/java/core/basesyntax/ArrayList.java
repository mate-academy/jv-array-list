package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_INCREASE = 1.5;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final String ILLEGAL_CAPACITY = "Illegal Capacity ";
    private static final String ILLEGAL_INDEX = "Illegal index ";
    private static final String MISSING_ITEM = "Item is missing in collection";
    private static final int NUMBER_ZERO = 0;
    private static final int NUMBER_ONE = 1;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = EMPTY_ELEMENTDATA;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > NUMBER_ZERO) {
            elementData = new Object[initialCapacity];
        } else if (initialCapacity == NUMBER_ZERO) {
            elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new RuntimeException(ILLEGAL_CAPACITY + initialCapacity);
        }
    }

    public ArrayList(List<T> collection) {
        elementData = new Object[collection.size()];
        if (elementData.length != NUMBER_ZERO) {
            for (int i = NUMBER_ZERO; i < collection.size(); i++) {
                add(collection.get(i));
            }
        } else {
            elementData = EMPTY_ELEMENTDATA;
        }
    }

    private void increaseArraySizeIfFull(int newSize) {
        if (newSize < elementData.length) {
            return;
        }
        if (elementData == EMPTY_ELEMENTDATA) {
            int initialElementDataSize = Math.max(DEFAULT_CAPACITY, newSize);
            elementData = new Object[initialElementDataSize];
            return;
        }
        int newElementDataSize = Math.max(
                (int) Math.ceil(CAPACITY_INCREASE * size + NUMBER_ONE),
                newSize
        );
        Object[] newArrTemp = new Object[newElementDataSize];
        System.arraycopy(elementData, NUMBER_ZERO, newArrTemp, NUMBER_ZERO, elementData.length);
        elementData = newArrTemp;
    }

    private void indexChecking(int index) {
        if (index < NUMBER_ZERO || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(ILLEGAL_INDEX + index);
        }
    }

    @Override
    public void add(T value) {
        increaseArraySizeIfFull(size + NUMBER_ONE);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < NUMBER_ZERO || index > size) {
            throw new ArrayListIndexOutOfBoundsException(ILLEGAL_INDEX + index);
        }
        if (index == size) {
            add(value);
            return;
        }
        increaseArraySizeIfFull(size + NUMBER_ONE);
        System.arraycopy(elementData, index, elementData, index + NUMBER_ONE, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = NUMBER_ZERO; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexChecking(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexChecking(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexChecking(index);
        T removedValue = (T) elementData[index];
        System.arraycopy(elementData,
                index + NUMBER_ONE,
                elementData,
                index,
                size - index + NUMBER_ONE
        );
        --size;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = NUMBER_ZERO; i < size; i++) {
            if (elementData[i] == element
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(MISSING_ITEM);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == NUMBER_ZERO;
    }

}
