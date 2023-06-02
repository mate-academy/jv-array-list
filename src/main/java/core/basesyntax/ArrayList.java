package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double MULTIPLIER_TO_INCREASE_THE_ARRAY = 1.5;
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
        if (size >= index) {
            if (size == arrayList.length) {
                arrayList = grow(1);
            }
        }
        System.arraycopy(arrayList, index,
                arrayList, index + 1,
                size - index);
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
                return remove(i);
            }
        }
        throw new NoSuchElementException("there is no such element present:" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    private T[] grow(int minGrow) {
        return arrayList = Arrays.copyOf(arrayList,
                (int) Math.max(arrayList.length + (arrayList.length
                        * MULTIPLIER_TO_INCREASE_THE_ARRAY), minGrow));
    }

    private void fastRemove(int i) {
        final int newSize = size - 1;
        if (newSize > i) {
            System.arraycopy(arrayList, i + 1, arrayList, i, newSize - i);
        }
        arrayList[newSize] = null;
        size = newSize;
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
