package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String INVALID_INDEX = "Index is invalid";
    private static final String INVALID_VALUE = "Value is invalid";
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    private T[] grow() {
        T[] newArray = (T[]) new Object[size + (size / 2)];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    private void checkIndex(int index, int size) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INVALID_INDEX);
        }
    }

    @Override
    public void add(T value) {
        if (array.length == size) {
            array = grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size);
        if (size == array.length) {
            array = grow();
        }
        System.arraycopy(array, index,
                array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {

        while (list.size() > array.length - size) {
            array = grow();
        }
        for (int i = 0; i < list.size(); i++) {
            array[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, size - 1);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size - 1);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T oldValue = (T) array[index];
        System.arraycopy(array, index + 1, array,
                index, size - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        boolean isElementExist = false;
        int i = 0;
        if (element == null) {
            for (; i < size; i++) {
                if (array[i] == null) {
                    remove(i);
                    isElementExist = true;
                    break;
                }
            }
        } else {
            for (; i < size; i++) {
                if (element.equals(array[i])) {
                    remove(i);
                    isElementExist = true;
                    break;
                }
            }
        }
        if (!isElementExist) {
            throw new NoSuchElementException(INVALID_VALUE);
        }
        return element;
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
