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
        if (index < 0 || index >= size)
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
    }

    private void checkRange(int index) {
        if (index < 0 || index >= elementData.length)
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
    }

    @Override
    public void add(T value) {
        //if ()
        add(value, elementData, size);
    }

    public T[] toArray(List<T> t) {
        T[] arr = (T[]) new Object[t.size()];
        for (int i = 0; i < t.size(); i++)
            arr[i] = t.get(i);
        return arr;
    }

    @Override
    public void add(T value, int index) {
        checkRange(index);
        //indexCheck(index);
        //final int s;
        //Object[] newElementData;
        //if ((s = size) == (newElementData = this.elementData).length)
            elementData = (T[]) grow(size +1);
        System.arraycopy(elementData, index, elementData, index + 1, elementData.length - 1 - index);
        elementData[index] = value;
        size = size + 1;
    }

    @Override
    public void addAll(List<T> list) {
        T[] listData = (T[]) new Object [list.size()];
        listData = list.toArray(list);
        int numNew = list.size();
        T[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size))
            elementData = (T[]) grow(s + numNew);
        System.arraycopy(listData, 0, elementData, s, numNew);
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
        T value = get(index);
        elementData = (T[]) grow(elementData.length + 1);
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        //T value = get()
        size--;
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


