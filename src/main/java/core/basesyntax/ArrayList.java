package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size = 0;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            capacity();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bound");
        }
        if (size == array.length) {
            capacity();
        }
        if (index == size) {
            array[size] = value;
        } else {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {

        T[] newArray = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            newArray[i] = list.get(i);
            add(newArray[i]);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(" Out of bounds");
        }
        return array[index];

    }

    @Override
    public void set(T value, int index) {
        if (!(index < 0 || index >= size)) {
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("No such index");
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds");
        }
        T res = array[index];
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
        return res;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], element)) {
                index = i;

            }
        }
        if (index != -1) {
            return remove(index);
        } else {
            throw new NoSuchElementException("Didn't find the element");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void capacity() {
        array = Arrays.copyOf(array, array.length + DEFAULT_CAPACITY / 2);
    }
}


