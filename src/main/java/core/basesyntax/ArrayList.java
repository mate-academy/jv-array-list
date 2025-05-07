package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private static final double GROWTH_INDEX = 1.5;
    private int size;
    private T[] defaultList;

    public ArrayList() {
        defaultList = (T[]) new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        growIfFull();
        defaultList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        validateAddingIndex(index);
        growIfFull();
        System.arraycopy(defaultList, index, defaultList, index + 1, size - index);
        defaultList[index] = value;
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
        validateIndex(index);
        return defaultList[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        defaultList[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T element = (T) defaultList[index];
        System.arraycopy(defaultList, index + 1, defaultList, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if ((defaultList[i] == element)
                    || (defaultList[i] != null && defaultList[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfFull() {
        if (size == defaultList.length) {
            generateBiggerArray();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add value here");
        }
    }

    private void validateAddingIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add value here");
        }
    }

    private void generateBiggerArray() {
        int newSize = size < DEFAULT_LENGTH ? DEFAULT_LENGTH : (int)
                ((int) defaultList.length * GROWTH_INDEX);
        T[] newList = (T[]) new Object[newSize];
        System.arraycopy(defaultList, 0, newList, 0, size);
        defaultList = newList;
    }
}
