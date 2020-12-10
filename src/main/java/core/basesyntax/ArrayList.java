package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private boolean isAvailable(int index) {
        return index >= 0 && index < size;
    }

    private void grow() {
        Object[] newArray = new Object[array.length + (array.length >> 1)];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = (T[]) newArray;

    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (!(index >= 0 && index <= size)) {
            throw new ArrayIndexOutOfBoundsException("Array Index Out of bound " + index);
        }
        if (size == array.length) {
            grow();
        }

        if (index == size) {
            array[size] = value;
            size++;
        } else if (index <= size - 1) {
            size++;
            System.arraycopy(array, index, array, index + 1,
                    array.length - index - 1);
            array[index] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (isAvailable(index)) {
            return array[index];
        }
        throw new ArrayIndexOutOfBoundsException("Array Index Out of bound " + index);
    }

    @Override
    public void set(T value, int index) {
        if (isAvailable(index)) {
            array[index] = value;
            return;
        }
        throw new ArrayIndexOutOfBoundsException("Array Index Out of bound " + index);
    }

    @Override
    public T remove(int index) {
        if (isAvailable(index)) {
            T delete = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            return delete;
        }
        throw new ArrayIndexOutOfBoundsException("Array Index Out of bound " + index);
    }

    @Override
    public T remove(T t) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (array[i] == t || (array[i] != null && array[i].equals(t))) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            return remove(index);
        }
        throw new NoSuchElementException("No such Element " + t);
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
