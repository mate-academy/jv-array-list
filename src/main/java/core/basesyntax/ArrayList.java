package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] array;
    private int realSize = 0;
    private int capacity = 10;

    public ArrayList() {
        array = (T[]) new Object[capacity];
    }

    private void isWrongIndex(int index)
            throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= realSize) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of range");
        }
    }

    private void grow() {
        if (realSize == capacity) {
            capacity = capacity + (capacity >> 1);
            T[] tempArray = (T[]) new Object[capacity];
            System.arraycopy(array, 0, tempArray, 0, realSize);
            array = tempArray;
        }
    }

    @Override
    public void add(T value) {
        grow();
        array[realSize] = value;
        realSize++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index > realSize) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of range");
        }
        grow();
        System.arraycopy(array, index, array, index + 1, realSize - index);
        array[index] = value;
        realSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        isWrongIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        isWrongIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        isWrongIndex(index);
        T removeValue = array[index];
        if (index != realSize - 1) {
            System.arraycopy(array, index + 1, array, index, realSize - index);
            array[realSize] = null;
        } else {
            array[index] = null;
        }
        realSize--;
        return removeValue;
    }

    @Override
    public T remove(T element)
            throws ArrayListIndexOutOfBoundsException, NoSuchElementException {
        for (int i = 0; i < realSize; i++) {
            if (isEqual(element, array[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Element was not found in List");
    }

    @Override
    public int size() {
        return realSize;
    }

    @Override
    public boolean isEmpty() {
        return realSize == 0;
    }

    private boolean isEqual(T firstElement, T secondElement) {
        return (firstElement == null && secondElement == null)
                || (firstElement != null && firstElement.equals(secondElement));
    }
}
