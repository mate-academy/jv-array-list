package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (array.length == size) {
            grow();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (!(checkIndex(index) || index == size)) {
            throw new ArrayIndexOutOfBoundsException("Add element failed. Index out of bounds");
        }
        if (index == size) {
            add(value);
        } else {
            if (size == array.length) {
                grow();
            }
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        }

    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (!checkIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("Getting element failed. Index out of bounds");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (!checkIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("Set element failed. Index out of bounds");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        T temp;
        if (!checkIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("Remove element failed. Index out of bounds");
        }
        temp = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        array[size - 1] = null;
        size--;
        return temp;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (array[i] == t || (array[i] != null && array[i].equals(t))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Remove element failed. No such element in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        array = Arrays.copyOf(array, (int) (array.length * 1.5));
    }

    private boolean checkIndex(int index) {
        return index < size && index >= 0;
    }
}
