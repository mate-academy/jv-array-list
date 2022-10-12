package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int FIRST_INDEX = 0;
    private static final int ONE = 1;
    private static final double INCREASE_INDEX = 1.5;
    private T[] currentArray;
    private int size;

    public ArrayList() {
        this.currentArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

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
        System.arraycopy(currentArray, index, currentArray, index + ONE, size - index);
        currentArray[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = FIRST_INDEX; i < list.size(); i++) {
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
        System.arraycopy(currentArray, index + ONE, currentArray,
                index, size - ONE - index);
        size--;
        return removedData;
    }

    @Override
    public T remove(T element) {
        T removedElement;
        for (int i = FIRST_INDEX; i < size; i++) {
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
        return size == FIRST_INDEX;
    }

    private void checkGrow() {
        if (size >= currentArray.length) {
            T[] newArr = (T[]) new Object[(int) (currentArray.length * INCREASE_INDEX)];
            System.arraycopy(currentArray, FIRST_INDEX, newArr, FIRST_INDEX, currentArray.length);
            currentArray = newArr;
        }
    }

    private void chekIndex(int index) {
        if (index >= size || index < FIRST_INDEX) {
            throw new ArrayListIndexOutOfBoundsException("Array list index of bound");
        }
    }
}
