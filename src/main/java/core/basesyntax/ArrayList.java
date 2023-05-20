package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;
    private static final int EMPTY = 0;
    private static final double GROW = 1.5;
    private int size;
    private int capacity;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[START_CAPACITY];
        size = EMPTY;
        capacity = START_CAPACITY;
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("IndexOutOfBounds");
        } else {
            if (size == capacity) {
                grow();
            }
            T[] newArray = (T[]) new Object[capacity];
            System.arraycopy(array, 0, newArray,0, index);
            newArray[index] = value;
            System.arraycopy(array, index, newArray,index + 1, size - index);
            array = newArray;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > capacity) {
            grow();
        }
        T[] acceptedArray = (T[]) new Object[list.size()];
        for (int i = 0; i < acceptedArray.length; i++) {
            acceptedArray[i] = list.get(i);
        }
        System.arraycopy(acceptedArray, 0, array, size, acceptedArray.length);
        size += acceptedArray.length;
    }

    @Override
    public T get(int index) {
        if (legalBound(index)) {
            throw new ArrayListIndexOutOfBoundsException("IndexOutOfBounds");
        } else {
            return array[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (legalBound(index)) {
            throw new ArrayListIndexOutOfBoundsException("IndexOutOfBounds");
        } else {
            array[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (legalBound(index)) {
            throw new ArrayListIndexOutOfBoundsException("IndexOutOfBounds");
        } else {
            T result = array[index];
            size--;
            System.arraycopy(array, index + 1, array, index, size - index);
            return result;
        }
    }

    @Override
    public T remove(T element) {
        T result = null;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] == element || array[i] != null && array[i].equals(element)) {
                result = element;
                index = i;
                break;
            }
        }
        if (result == null && element != null) {
            throw new NoSuchElementException();
        } else {
            size--;
            System.arraycopy(array, index + 1, array, index, size - index);
            return result;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY;
    }

    private void grow() {
        capacity = (int) (capacity * GROW);
        T[] newArray = (T[]) new Object[capacity];
        System.arraycopy(array, 0, newArray,0, size);
        array = newArray;
    }

    private boolean legalBound(int index) {
        if (index < 0 || index > size - 1) {
            return true;
        }
        return false;
    }
}
