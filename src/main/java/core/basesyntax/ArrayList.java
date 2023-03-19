package core.basesyntax;

import java.time.chrono.MinguoDate;
import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private T[] array;
    int size = 0;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    public ArrayList(int size) {
        if (size <= DEFAULT_ARRAY_SIZE) {
            array = (T[]) new Object[DEFAULT_ARRAY_SIZE];
        } else {
            array = (T[]) new Object[size];
        }
    }



    @Override
    public void add(T value) {
        if(size >= array.length) {
            grow();
        }
        array[size - 1] = value;
        size++;

    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        while (index > size && index > array.length || index + 1 > array.length) {
            grow();
        }
        if (index < size) {
            System.arraycopy(array, index, array, index + 1, array.length - index);
            array[index] = value;
        } else {
            array[index] = value;
        }



    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() > array.length - size()) {
            grow();
        }
        System.arraycopy(list, 0, array, size(), list.size());
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        T temp = null;
        if (index < size()) {
            temp = array[index];
            array[index] = null;
            System.arraycopy(array, index + 1, array, index - 1, size - index - 1);
        }
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                return this.remove(i);
            }
        }
        throw new NoSuchElementException("");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void grow() {
        array = (T[]) new Object[(int) Math.ceil(array.length * 1.5)];
    }
}

