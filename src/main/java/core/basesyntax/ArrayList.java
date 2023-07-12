package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASING_COEFFICIENT = 1.5;
    private int currentSize;
    private T[] array;

    public ArrayList() {
        this.currentSize = 0;
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        array[currentSize] = value;
        currentSize++;
    }

    @Override
    public void add(T value, int index) {
        checkCapacity();
        indexInRange(index, currentSize + 1);
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
        indexInRange(index, currentSize);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexInRange(index, currentSize);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkCapacity();
        indexInRange(index, currentSize);
        final T removeElement = array[index];
        System.arraycopy(array, index + 1, array, index, currentSize - index);
        array[currentSize - 1] = null;
        currentSize--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < currentSize; i++) {
            if (element == array[i] || (element != null && element.equals(array[i]))) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element in array! : " + element);
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void checkCapacity() {
        if (array.length == currentSize) {
            T[] tmpArray = array;
            array = (T[]) new Object[(int) (array.length * INCREASING_COEFFICIENT)];
            System.arraycopy(tmpArray, 0, array, 0, currentSize);
        }
    }

    public void indexInRange(int index, int maxBound) {
        if (index >= maxBound || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index ["
                    + index + "] out of array range!");
        }
    }
}

