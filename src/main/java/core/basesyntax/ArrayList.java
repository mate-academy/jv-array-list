package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double LENGTH_MULTIPLIER = 1.5;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            resize();
        }
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        if (arrayList.length == size) {
            resize();
        }
        checkIndex(index);
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
        checkIndex(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T tempList = arrayList[index];
        System.arraycopy(arrayList,index + 1, arrayList, index, size - index - 1);
        size--;
        return tempList;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (compare(element, arrayList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Not matches found with element - \"" + element + "\"");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean compare(T firstValue, T secondValue) {
        return firstValue == secondValue || firstValue != null && firstValue.equals(secondValue);
    }

    private void resize() {
        int newCapacity = (int) (arrayList.length * LENGTH_MULTIPLIER);
        T[] resizedList = (T[]) new Object[newCapacity];
        System.arraycopy(arrayList, 0, resizedList, 0, arrayList.length);
        arrayList = resizedList;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index - " + index
                    + " out of bounds for length: " + arrayList.length);
        }
    }
}
