package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T[] entryArray = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        extendArrayLength();
        entryArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is out of bounds");
        }
        extendArrayLength();
        System.arraycopy(entryArray, index, entryArray, index + 1, size - index);
        entryArray[index] = value;
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
        checkIndexValidity(index);
        return (T) entryArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexValidity(index);
        entryArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexValidity(index);
        T element = (T) entryArray[index];

        reduceTheLengthOfTheArray(index);
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (entryArray[i] == null || entryArray[i].equals(element)) {
                reduceTheLengthOfTheArray(i);
                return element;
            }
        }
        throw new NoSuchElementException("No Such Element in array: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void extendArrayLength() {
        if (entryArray.length <= size) {
            int growFactor = (int) (entryArray.length * GROWTH_FACTOR);
            T[] newArray = (T[]) new Object[growFactor];
            System.arraycopy(entryArray, 0, newArray, 0, size);
            entryArray = newArray;
        }
    }

    private void checkIndexValidity(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is out of bounds: index-" + index);
        }
    }

    private void reduceTheLengthOfTheArray(int i) {
        System.arraycopy(entryArray, i + 1, entryArray, i, size - i - 1);
        entryArray[size - 1] = null;
        size--;
    }
}
