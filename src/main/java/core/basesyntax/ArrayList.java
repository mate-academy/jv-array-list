package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int THIS_IS_ZERO = 0;
    private static final int THIS_IS_ONE = 1;
    private static final int THIS_IS_TWO = 2;
    private T[] currentArray = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        checkGrow();
        currentArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        size++;
        chekIndex(index);
        checkGrow();
        System.arraycopy(currentArray, index, currentArray, index + THIS_IS_ONE, size - index);
        currentArray[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = THIS_IS_ZERO; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        chekIndex(index);
        return currentArray[index];
    }

    @Override
    public void set(T value, int index) {
        chekIndex(index);
        currentArray[index] = value;
    }

    @Override
    public T remove(int index) {
        chekIndex(index);
        T removedData = currentArray[index];
        System.arraycopy(currentArray, index + THIS_IS_ONE, currentArray,
                index, size - THIS_IS_ONE - index);
        size--;
        return removedData;
    }

    @Override
    public T remove(T element) {
        T removedElement;
        for (int i = THIS_IS_ZERO; i < size; i++) {
            if (element == currentArray[i] || element != null && element.equals(currentArray[i])) {
                removedElement = currentArray[i];
                remove(i);
                return removedElement;
            }
        }
        throw new NoSuchElementException("Element not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == THIS_IS_ZERO;
    }

    public void checkGrow() {
        if (size == currentArray.length) {
            currentArray = Arrays.copyOf(currentArray, currentArray.length
                    + currentArray.length / THIS_IS_TWO);
        }
    }

    public void chekIndex(int index) {
        if (index >= size || index < THIS_IS_ZERO) {
            throw new ArrayListIndexOutOfBoundsException("Array list index of bound");
        }
    }
}
