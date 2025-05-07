package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final String BOUNDS_EXCEPTION = "Out of bounds exception";
    public static final String NO_ELEMENT_EXCEPTION = "Element is not exist";
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        resize();
        System.arraycopy(array,index,array,index + 1, size - index);
        array[index] = value;
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
        checkIndex(index + 1);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index + 1);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index + 1);
        T value = array[index];
        System.arraycopy(array,index + 1,array,index, size - index - 1);
        array[--size] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((array[i] == element) || (element != null && element.equals(array[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_ELEMENT_EXCEPTION);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (size == array.length) {
            int newLength = array.length + (array.length / 2);
            T[] newArray = (T[]) new Object[newLength];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    public void checkIndex(int checkIndex) {
        if (checkIndex > size || checkIndex < 0) {
            throw new ArrayListIndexOutOfBoundsException(BOUNDS_EXCEPTION);
        }
    }

}
