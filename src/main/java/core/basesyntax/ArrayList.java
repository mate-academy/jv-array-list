package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MULTIPLE_CONSTANT = 1.5;
    private int size = 0;
    private T[] array;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void grow() {
        T[] newArray = (T[]) new Object[(int) (array.length * MULTIPLE_CONSTANT)];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (index == size) {
            add(value);
        } else if (index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
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
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Object object = array[index];
        System.arraycopy(array, index + 1, array, index, size - (index + 1));
        size--;
        return (T) object;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < array.length; i++) {
            if (t == null) {
                size--;
                return null;
            }
            if (t.equals(array[i])) {
                T object = (T) new Object();
                object = t;
                System.arraycopy(array, i + 1, array, i, size - (i + 1));
                size--;
                return object;
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
}
