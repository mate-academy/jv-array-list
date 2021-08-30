package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] defaultArray;

    public ArrayList() {
        defaultArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == defaultArray.length) {
            defaultArray = grow();
        }
        defaultArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is not correct!");
        }
        if (size == defaultArray.length) {
            defaultArray = grow();
        }
        System.arraycopy(defaultArray, index, defaultArray, index + 1, size - index);
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
        T element = defaultArray[index];
        System.arraycopy(defaultArray, index + 1, defaultArray, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (defaultArray[i] == element
                    || defaultArray[i] != null && defaultArray[i].equals(element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("This element doesn't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        T[] arrayWithANewSize = (T[]) new Object[size + (size >> 1)];
        System.arraycopy(defaultArray, 0, arrayWithANewSize, 0, defaultArray.length);
        return arrayWithANewSize;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is not correct!");
        }
    }
}

