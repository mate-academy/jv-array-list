package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIALIZATION_LENGTH = 10;
    private static final String ERROR_MESSAGE_INDEX = "Your index is incorrect";
    private static final String ERROR_MESSAGE_ELEMENT = "Can't remove element for such";
    private static final double GROW_INDEX = 1.5;
    private T[] array;
    private int size = 0;

    public ArrayList() {
        array = (T[])new Object[INITIALIZATION_LENGTH];
    }

    @Override
    public void add(T value) {
        makeBiggerArrayIfNeeded();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(ERROR_MESSAGE_INDEX);
        }
        makeBiggerArrayIfNeeded();
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
        checkIndex(index);
        return (T)array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T returnValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return returnValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || element != null && element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(ERROR_MESSAGE_ELEMENT + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(ERROR_MESSAGE_INDEX);
        }
    }

    private void makeBiggerArrayIfNeeded() {
        if (size == array.length) {
            int newSize = (int) (size * 1.5);
            T[] newArray = (T[]) new Object[newSize];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }
}
