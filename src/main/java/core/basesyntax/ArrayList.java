package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE_CAPACITY = 10;
    private static final int ADDING_SIZE = 5;
    private T[] array;
    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[INITIAL_SIZE_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            array = grow(array);
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds: " + index + ".");
        }
        if (size == array.length) {
            array = grow(array);
        }
        System.arraycopy(array, index, array, index + 1, size - index);
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
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds: " + index + ".");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds: " + index + ".");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds: " + index + ".");
        }
        T oldValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == null && array[i] == null || array[i] != null && array[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow(T[] arrayValues) {
        T[] temporaryArray = arrayValues;
        arrayValues = (T[]) new Object[arrayValues.length + ADDING_SIZE];
        System.arraycopy(temporaryArray, 0, arrayValues, 0, temporaryArray.length);
        return arrayValues;
    }
}
