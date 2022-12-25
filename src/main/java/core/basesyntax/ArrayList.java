package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_INDEX = 0;
    private static final int INITIAL_CAPACITY = 10;
    private T[] defaultArray;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        defaultArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == defaultArray.length) {
            defaultArray = (T[]) grow(defaultArray);
        }
        defaultArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (++size == defaultArray.length) {
            defaultArray = (T[]) grow(defaultArray);
        }
        System.arraycopy(defaultArray, index, defaultArray, index + 1, size - index);
        defaultArray[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > defaultArray.length) {
            defaultArray = (T[]) grow(defaultArray);
        }
        addElementArray(list);
    }

    private void addElementArray(List<T> list) {
        Object[] tempList = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
        System.arraycopy(tempList, DEFAULT_INDEX, defaultArray, size, tempList.length);
        size += list.size();
    }

    public Object[] grow(Object[] array) {
        int arrayLength = defaultArray.length + (defaultArray.length / 2);
        Object[] temp = new Object[arrayLength];
        System.arraycopy(array, DEFAULT_INDEX, temp, DEFAULT_INDEX, size);
        return temp;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " out of bounds, for length " + size());
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T)defaultArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        defaultArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) defaultArray[index];
        System.arraycopy(defaultArray, index + 1, defaultArray, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element != null && element.equals(defaultArray[i]))
                    || element == defaultArray[i]) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can not remove. The element "
                + element + " does not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == DEFAULT_INDEX;
    }
}
