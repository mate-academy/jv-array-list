package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private int size = 0;
    private T[] array;

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
        if (size >= array.length) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        while (index > size && index > array.length || size + 1 > array.length) {
            grow();
        }
        if (index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        } else {
            array[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() > array.length - size()) {
            grow();
        }
        System.arraycopy(toArray(list), 0, array, size(), list.size());
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index + 1 > size) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        T temp = null;
        if (index < size()) {
            temp = array[index];
            array[index] = null;
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                return this.remove(i);
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
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void grow() {
        T[] copyArray = (T[]) new Object[size];
        System.arraycopy(array, 0, copyArray, 0, size);
        array = (T[]) new Object[(int) Math.ceil(array.length * 1.5)];
        System.arraycopy(copyArray, 0, array, 0, size);
    }

    private T[] toArray(List<T> list) {
        T[] array = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}


