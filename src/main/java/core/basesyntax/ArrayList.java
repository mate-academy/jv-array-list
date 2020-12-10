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

    private void grow() {
        Object[] newArray = new Object[array.length * 3 / 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = (T[]) newArray;

    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            if (size == array.length) {
                grow();
            }
            array[size] = value;
            size++;
        } else if (index <= size - 1) {
            size++;
            if (size == array.length) {
                grow();
            }
            System.arraycopy(array, index, array, index + 1,
                    array.length - index - 1);
            array[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException("Array Index Out of bound " + index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        } else {
            throw new ArrayIndexOutOfBoundsException("Array Index Out of bound " + index);
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            array[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException("Array Index Out of bound " + index);
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T delete = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            return delete;
        } else {
            throw new ArrayIndexOutOfBoundsException("Array Index Out of bound " + index);
        }
    }

    @Override
    public T remove(T t) {
        T oldValue = t;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (array[i] == t || (array[i] != null && array[i].equals(t))) {
                index = i;
                oldValue = array[i];
                break;
            }
        }
        if (index != -1) {
            remove(index);
        } else {
            throw new NoSuchElementException("No such Element " + t);
        }
        return oldValue;
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
