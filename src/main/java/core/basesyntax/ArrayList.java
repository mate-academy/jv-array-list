package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    T [] arrayListData;
    private int size = 0;

    public ArrayList() {
        arrayListData = (T[]) new Object[DEFAULT_CAPACITY];
    }



    private T[] grow() {
        return grow(size + 1);
    }

    private T[] grow(int minCapacity) {
        return arrayListData = Arrays.copyOf(arrayListData,
                newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = arrayListData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        return newCapacity;

    }

    private void add(T value, T[] arrayListData, int s) {
        if (s == arrayListData.length)
            arrayListData = grow();
        arrayListData[s] = value;
        size = s + 1;
    }

    @Override
    public void add(T value) {

        if (size < arrayListData.length) {
            add(value, arrayListData, size);
        } else if (size > arrayListData.length && size < Integer.MAX_VALUE - 8) {
            grow();
            add(value, arrayListData, size);
        }


    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        arrayListData[index] = value;

    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return arrayListData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayListData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size) {
            throw new NoSuchElementException("index");
        }
        checkIndex(index);
        T removedElement = arrayListData[index];
        for (int i = 0; i < size - 1; i++) {
            arrayListData[i] = arrayListData[i + 1];
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        T removedElement = null;
        int startPoint = 0;
        int initialSize = size;
        for (int i = 0; i < size; i++) {
            if (arrayListData[i] != null && arrayListData[i].equals(element)) {
                removedElement = arrayListData[i];
                startPoint = i;
            }
        }
            if (size - 1 - startPoint >= 0)
                System.arraycopy(arrayListData, startPoint + 1,
                        arrayListData, startPoint, size - 1 - startPoint);
            size--;
        if (initialSize == size) {throw new NoSuchElementException("value");}
        return removedElement;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    private void checkIndex(int index) {
        if (size < index || index < 0)
        throw new ArrayListIndexOutOfBoundsException("Checked index is wrong");
    }
}
