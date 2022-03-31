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

    @Override
    public void add(T e) {
        if (size == arrayListData.length) {
            ensureCapacity();
        }
        arrayListData[size++] = e;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        arrayListData[index] = value;

    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
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

    private void ensureCapacity() {
        int increasedCapacity = arrayListData.length + (arrayListData.length >> 1);
        arrayListData = Arrays.copyOf(arrayListData, increasedCapacity);
    }
}
