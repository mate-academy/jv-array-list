package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] defaultArray;
    private int size = 0;

    public ArrayList() {
        this.defaultArray = (T[]) new Object[DEFAULT_SIZE];
    }

    private void foundSize() {
        if (size == defaultArray.length) {
            T[] destinationArray = (T[]) new Object[(int) (defaultArray.length * 1.5)];
            System.arraycopy(defaultArray, 0, destinationArray, 0, defaultArray.length);
            defaultArray = destinationArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist " + index);
        }
    }

    @Override
    public void add(T value) {
        foundSize();
        defaultArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            foundSize();
            defaultArray[index] = value;
            size++;
            return;
        }
        if (index >= 0 && index < size) {
            foundSize();
            System.arraycopy(defaultArray, index, defaultArray, index + 1, size - index);
            defaultArray[index] = value;
            size++;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Incorrect index " + index);
    }

    @Override
    public void addAll(List<T> list) {
        foundSize();
        int newSize = 0;
        while (newSize < list.size()) {
            defaultArray[size] = list.get(newSize);
            size++;
            newSize++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return defaultArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        defaultArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = defaultArray[index];
        size--;
        System.arraycopy(defaultArray, index + 1, defaultArray, index, size - index);
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (defaultArray[i] == element || defaultArray[i] != null
                                    && defaultArray[i].equals(element)) {
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
}
