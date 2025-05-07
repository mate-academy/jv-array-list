package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Double MULTIPLIER = 1.5;
    private Object[] internalArray;
    private int size;

    public ArrayList() {
        this.internalArray = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkLengthAndGrow();
        internalArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        checkLengthAndGrow();
        System.arraycopy(internalArray, index, internalArray, index + 1, size - index);
        internalArray[index] = value;
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
        return (T) internalArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        internalArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        size--;
        T removalElement = (T) internalArray[index];
        System.arraycopy(internalArray,index + 1, internalArray, index,
                    internalArray.length - index - 1);
        return removalElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == (T) internalArray[i] || (element != null
                    && element.equals((T) internalArray[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " doesn't exist.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkLengthAndGrow() {
        if (size == internalArray.length) {
            int newLength = (int) (internalArray.length * MULTIPLIER);
            Object[] internalArrayTemp = new Object[newLength];
            System.arraycopy(internalArray,0, internalArrayTemp, 0, internalArray.length);
            internalArray = internalArrayTemp;
        }
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index = " + index
                    + ". Index can't be negative");
        }
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Element with index " + index
                    + " doesn't exist. Size of array is only " + size);
        }
    }
}
