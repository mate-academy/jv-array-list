package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double AMMOUT_OF_INCREASE = 1.5;
    private Object[] array;
    private int currentSize;

    public ArrayList() {
        array = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (currentSize == array.length) {
            increaseSize();
        }
        array[currentSize] = value;
        currentSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > currentSize) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        if (currentSize == array.length) {
            increaseSize();
        }
        System.arraycopy(array, index, array,index + 1, currentSize - index);
        array[index] = value;
        currentSize++;
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
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {

        checkIndex(index);
        Object deletedEl = array[index];
        System.arraycopy(array, index + 1, array, index, currentSize - 1 - index);
        currentSize--;
        return (T) deletedEl;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < currentSize; i++) {
            if (element != null && element.equals(array[i]) || element == array[i]) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can`t find element " + element + " in list");
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {

        return currentSize == 0;
    }

    private void checkIndex(int index) {
        if (index >= currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %s out of bound for length %s", index, currentSize));
        }
    }

    public void increaseSize() {
        Object[] newArray = (T[]) new Object[(int) (currentSize * AMMOUT_OF_INCREASE)];
        System.arraycopy(array, 0, newArray, 0, currentSize);
        array = newArray;
    }
}
