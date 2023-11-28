package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private int size;
    private T[] elementData;
    public static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    public ArrayList() {
        elementData = (T[]) new Object [DEFAULT_CAPACITY];
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0)
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
    }

    @Override
    public void add(T value) {
        add(value, elementData, size);
        //size++;

    }

    @Override
    public void add(T value, int index) {
        indexCheck(index);
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length)
            elementData = grow();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = value;
        size = s + 1;
    }

    @Override
    public void addAll(List<T> list) {
        //Object[] a = c.toArray();
       // modCount++;
        int numNew = list.size();
        //if (numNew == 0)
            //return false;
        T[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size))
            elementData = (T[]) grow(s + numNew);
        System.arraycopy(elementData, 0, this.elementData, s, numNew);
        size = s + numNew;
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T[] newElementData = (T[]) new  Object[elementData.length];
        System.arraycopy(elementData, index , newElementData, index + 1, size - 1);
        size--;
        return elementData[index];
    }

    @Override
    public T remove(T element) {
        size--;
        return null;
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
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = oldCapacity + (minCapacity - oldCapacity) + (oldCapacity >> 1);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = (T[]) new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private void add(T e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }
}


