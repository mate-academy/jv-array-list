package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object [DEFAULT_CAPACITY];
    }

    public T[] toArray(List<T> t) {
        T[] arr = (T[]) new Object[t.size()];
        for (int i = 0; i < t.size(); i++) {
            arr[i] = t.get(i);
        }
        return arr;
    }

    private void add(T e, Object[] elementData, int s) {
        if (s == this.elementData.length) {
            this.elementData = (T[]) grow(size + 1);
        }
        this.elementData[s] = e;
        size = s + 1;
    }

    @Override
    public void add(T value) {
        add(value, elementData, size);
    }

    @Override
    public void add(T value, int index) {
        checkRange(index);
        elementData = (T[]) grow(size + 1);
        System.arraycopy(elementData, index, elementData,
                index + 1, elementData.length - index - 1);
        size++;
        indexCheck(index);
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        int sizeList = list.size();
        T[] listData = (T[]) new Object [sizeList];
        listData = list.toArray(list);
        if (sizeList > (this.elementData.length - size)) {
            elementData = (T[]) grow(size + sizeList);
        }
        System.arraycopy(listData, 0, this.elementData, size, sizeList);
        size = size + sizeList;
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
        elementData = (T[]) grow(elementData.length + 1);
        T value = get(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || elementData[i] != null && elementData[i].equals(element)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
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

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Non existed position");
        }
    }

    private void checkRange(int index) {
        if (index < 0 || index >= elementData.length) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = oldCapacity + (minCapacity - oldCapacity) + (oldCapacity >> 1);
            T[] increasedElementData = (T[]) new Object[newCapacity];
            System.arraycopy(this.elementData, 0, increasedElementData, 0, size);
            return increasedElementData;
        } else {
            return elementData = (T[]) new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }
}


