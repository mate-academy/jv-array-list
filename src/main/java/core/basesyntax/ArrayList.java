package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] entryArray;
    private int size;

    public ArrayList() {
        entryArray = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void extendArrayLength() {
        int oldArrayLength = entryArray.length;
        int newArrayLength = oldArrayLength + (oldArrayLength >> 1);
        entryArray = Arrays.copyOf(entryArray, newArrayLength);
    }

    @Override
    public void add(T value) {
        if (entryArray.length <= size) {
            extendArrayLength();
        }
        entryArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is too big");
        }
        if (entryArray.length <= size) {
            extendArrayLength();
        }
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is too big");
        }
        return (T) entryArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is out of bounds");
        }
        entryArray[index] = value;
    }

    @Override
    public T remove(int index) {
        T element;
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is out of bounds");
        } else {
            element = (T) entryArray[index];
        }

        System.arraycopy(entryArray, index + 1, entryArray, index, size - index - 1);
        entryArray[size - 1] = null;
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (entryArray[i] == null || entryArray[i].equals(element)) {
                System.arraycopy(entryArray, i + 1, entryArray, i, size - i - 1);
                entryArray[size - 1] = null;
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("No Such Element in array");
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
