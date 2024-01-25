package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    //private static final Object[] EMPTY_ELEMENT_DATA = {};
    private T[] arrayList;
    private int size;

    public ArrayList() {
        this.arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        boundCheckForAdd(index);
        if (size == arrayList.length) {
            arrayList = grow();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
        arrayList[index] = value;
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
        boundCheck(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        boundCheck(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        boundCheck(index);
        T oldValue = arrayList[index];
        fastRemove(index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arrayList.length; i++) {
            if (element == arrayList[i]
                    || element != null && element.equals(arrayList[i])) {
                T oldValue = arrayList[i];
                fastRemove(i);
                return oldValue;
            }
        }
        throw new NoSuchElementException("There is no such element present: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        return arrayList = Arrays.copyOf(arrayList, arrayList.length + (arrayList.length >> 1));
    }

    private void fastRemove(int i) {
        final int newSize = size - 1;
        if (newSize > i) {
            System.arraycopy(arrayList, i + 1, arrayList, i, newSize - i);
        }
        arrayList[newSize] = null;
        size = newSize;
    }

    private void boundCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index: " + index
                    + " is out of actual size " + size);
        }
    }

    private void boundCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index: " + index
                    + " is out of actual size " + (size - 1));
        }
    }
}
