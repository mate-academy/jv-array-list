package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int size;
    private T[] elementData;
    public static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    public ArrayList() {
        elementData = (T[]) new Object [DEFAULT_CAPACITY];
    }

    private void indexCheck(int index) {
        if (index >= size() || index < 0)
            throw new ArrayListIndexOutOfBoundsException("Non existed position");
    }

    private void checkRange(int index) {
        if (index < 0 || index >= elementData.length)
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
    }

    @Override
    public void add(T value) {
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
        elementData = (T[]) grow(size +1);
        System.arraycopy(elementData, index, elementData, index + 1, elementData.length - index - 1);
        size++;
        indexCheck(index);
        elementData[index] = value;
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
        checkRange(index);
        T value = get(index);
        elementData = (T[]) grow(elementData.length + 1);
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = -2;
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == element) {
                index = i;
                break;
            }
        }
            if (index == -2) {
                throw new NoSuchElementException();
            } else {
                remove(index);

        }

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


