package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;

    private int size;

    private Object[] dataArray;

    public ArrayList() {
        this.dataArray = new Object[START_CAPACITY];
        this.size = 0;
    }

    private boolean isArrayFull() {
        return size == dataArray.length;
    }

    private int getNewCapacity(int minCapacity) {
        int oldCapacity = dataArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        return newCapacity;
    }

    private void grow(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(dataArray, 0, newArray, 0, size);
        dataArray = newArray;
    }

    private void checkIndex(int index, boolean allowEqualSize) {
        if (index < 0 || index > size || (!allowEqualSize && index == size)) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds, for index: "
                    + index);
        }
    }

    private void shiftLeftFromIndex(int index) {
        System.arraycopy(dataArray, index + 1, dataArray, index, size - index - 1);
        dataArray[size - 1] = null;
        size--;
    }

    @Override
    public void add(T value) {
        if (isArrayFull()) {
            int newCapacity = getNewCapacity(size + 1);
            grow(newCapacity);
        }
        dataArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index,true);
        if (isArrayFull()) {
            int newCapacity = getNewCapacity(size + 1);
            grow(newCapacity);
        }
        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
        dataArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.isEmpty()) {
            return;
        }

        int newSize = size + list.size();
        if (newSize > dataArray.length) {
            int newCapacity = getNewCapacity(newSize);
            grow(newCapacity);
        }

        for (int i = 0; i < list.size(); i++) {
            dataArray[size + i] = list.get(i);
        }

        size = newSize;
    }

    @Override
    public T get(int index) {
        checkIndex(index,false);
        return (T) dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index,false);
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index,false);
        T removedValue = (T) dataArray[index];
        shiftLeftFromIndex(index);
        return removedValue;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (dataArray[i] == null) {
                    final T removedElement = (T) dataArray[i];
                    shiftLeftFromIndex(i);
                    return removedElement;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(dataArray[i])) {
                    final T removedElement = (T) dataArray[i];
                    shiftLeftFromIndex(i);
                    return removedElement;
                }
            }
        }
        throw new NoSuchElementException("No such element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
