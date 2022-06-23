package core.basesyntax;
//import jdk.internal.util.ArraysSupport;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    transient T[] arrayData = (T[]) new Object[DEFAULT_CAPACITY];
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private int size;
    private int cursor; // index of next element
    //private int lastReturned = -1;  // index of last element returned; -1 if no such

    private void rangeCheckForIndex(int index) {
        if (index > size || index < 0)
            throw new ArrayListIndexOutOfBoundsException("Index of bounds exception");
    }

    private Object[] grow(int minimalCapacity) {
        int oldCapacity = arrayData.length;
        double multiplicator = 1;
        if (oldCapacity == size) {
            multiplicator = 1.5;
        }
        if (oldCapacity > 0 || arrayData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = (int) (oldCapacity * multiplicator + 1);
            return arrayData = Arrays.copyOf(arrayData, newCapacity);
        } else {
            return arrayData = (T[]) new Object[Math.max(DEFAULT_CAPACITY, minimalCapacity)];
        }
    }

    public static Object[] listToArray(List<?> list) {
        if (!list.isEmpty()) {
            Object[] tempArray = new Object[list.size()];
            for (int index = 0; index < list.size(); index++)
                tempArray[index] = list.get(index);
            return tempArray;
        }
        return null;
    }

    @Override
    public void add(T value) {
        try {
            int index = cursor;
            ArrayList.this.add(value, index);
            cursor = index + 1;
            //lastReturned = -1;
        } catch (IndexOutOfBoundsException ex) {
            throw new ArrayListIndexOutOfBoundsException("Index of bounds exception");
        }
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForIndex(index);
        final int s;
        if ((s = size) == arrayData.length)
            arrayData = (T[]) grow(size + 1);
        System.arraycopy(arrayData, index,
                arrayData, index + 1,
                s - index);
        arrayData[index] = value;
        size = s + 1;
    }

    @Override
    public void addAll(List<T> list) {
        if (!list.isEmpty()) {
            T[] tempArray = (T[]) listToArray(list);
            int newCapacity = size + tempArray.length;
            arrayData = Arrays.copyOf(arrayData, newCapacity);
            System.arraycopy(tempArray, 0, arrayData, size, tempArray.length);
        }
    }

    @Override
    public T get(int index) {
        rangeCheckForIndex(index);
        return arrayData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForIndex(index);
        arrayData[index] = value;
    }

    @Override
    public T remove(int index) {
        //rangeCheckForIndex(index);
        T oldValue = get(index);
        int newSize = size - 1;
        if (newSize > index)
            System.arraycopy(arrayData, index + 1, arrayData, index, newSize - index);
        arrayData[size = newSize] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {

        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return arrayData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA ? true : false;
    }
}
