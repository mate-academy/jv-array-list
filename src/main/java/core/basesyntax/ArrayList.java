package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] defaultArray;
    private int size;

    public ArrayList() {
        defaultArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == defaultArray.length) {
            increaseArraySize();
        }
        defaultArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        if (size == defaultArray.length) {
            increaseArraySize();
        }
        System.arraycopy(defaultArray, index, defaultArray,
                index + 1, size - index);
        defaultArray[index] = value;
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
        System.arraycopy(defaultArray, index + 1, defaultArray, index, size - 1 - index);
        defaultArray[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == defaultArray[i]
                    || (element != null && element.equals(defaultArray[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " doesn`t exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseArraySize() {
        T[] temporaryArray = defaultArray;
        defaultArray = (T[]) new Object[defaultArray.length + defaultArray.length * 3 / 2];
        System.arraycopy(temporaryArray, 0, defaultArray, 0, size);
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }
}
