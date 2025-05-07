package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASING_COEFFICIENT = 1.5;
    private int currentSize;
    private T[] array;

    public ArrayList() {
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
        isIndexInRange(index, currentSize + 1);
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
        isIndexInRange(index, currentSize);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexInRange(index, currentSize);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkCapacity();
        isIndexInRange(index, currentSize);
        final T removeElement = array[index];
        System.arraycopy(array, index + 1, array, index, currentSize - index);
        array[currentSize - 1] = null;
        currentSize--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < currentSize; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                return remove(i);
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

    private void checkCapacity() {
        if (array.length == currentSize) {
            T[] newArray = array;
            array = (T[]) new Object[(int) (array.length * INCREASING_COEFFICIENT)];
            System.arraycopy(newArray, 0, array, 0, currentSize);
        }
    }

    private void isIndexInRange(int index, int maxBound) {
        if (index >= maxBound || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index ["
                    + index + "] out of array range!");
        }
    }
}
