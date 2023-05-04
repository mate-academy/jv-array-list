package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] array;
    private int currentSize;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (currentSize >= array.length) {
            grow();
        }
        array[currentSize] = value;
        currentSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index == currentSize) {
            add(value);
            return;
        } else {
            checkIndex(index);
        }
        if (currentSize >= array.length) {
            grow();
        }
        System.arraycopy(array, index, array, index + 1, currentSize - index);
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
        T tempElement = array[index];
        System.arraycopy(array, index + 1, array, index, currentSize - index - 1);
        currentSize--;        
        return tempElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < currentSize; i++) {
            if ((element != null && element.equals(array[i])) || array[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " is not exist!");
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void grow() {
        Object[] tempArray = (T[]) new Object[currentSize + (currentSize >> 1)];
        System.arraycopy(array, 0, tempArray, 0, currentSize);
        array = (T[]) tempArray;
    }

    private void checkIndex(int index) throws 
            ArrayIndexOutOfBoundsException {
        if (index >= currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " does not exist");
        }
    }
}
