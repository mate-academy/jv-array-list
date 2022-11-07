package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        boundsCheckForAdd(index);
        if (size == arrayList.length) {
            arrayList = grow(1);
        }
        System.arraycopy(arrayList, index,
                arrayList, index + 1,
                size - index);
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] a = (T[]) new Object[list.size()];
        int minGrow;
        for (int i = 0; i < list.size(); i++) {
            a[i] = list.get(i);
        }
        if (a.length > (arrayList.length - size)) {
            minGrow = a.length - (arrayList.length - size);
            arrayList = grow(minGrow);
        }
        System.arraycopy(a, 0, arrayList, size, a.length);
        size += a.length;
    }

    private T[] grow(int minGrow) {
        return arrayList = Arrays.copyOf(arrayList,
                Math.max(arrayList.length + (arrayList.length >> 1), minGrow));
    }

    @Override
    public T get(int index) {
        boundsCheck(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        boundsCheck(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        boundsCheck(index);
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
        throw new NoSuchElementException("there is no such element present:" + element);
    }

    private void fastRemove(int i) {
        final int newSize = size - 1;
        if (newSize > i) {
            System.arraycopy(arrayList, i + 1, arrayList, i, newSize - i);
        }
        arrayList[newSize] = null;
        size = newSize;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    private void boundsCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index: " + index
                    + " is out of actual size: " + size);
        }
    }

    private void boundsCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index: " + index
                    + " is out of actual size: " + (size - 1));
        }
    }
}
