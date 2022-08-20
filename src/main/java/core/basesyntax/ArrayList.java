package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAYLIST_SIZE = 10;
    private int size;

    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_ARRAYLIST_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            growArray();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        if (size == array.length) {
            growArray();
        }
        System.arraycopy(array, index, array, index + 1,array.length - index - 1);
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
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array list range");
        }
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array list range");
        }
        checkIndex(index);
        array[index] = value;
        if (index > size) {
            size = index;
        }
    }

    @Override
    public T remove(int index) {
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array list range");
        }
        checkIndex(index);
        T result = array[index];
        System.arraycopy(array,index + 1, array, index,array.length - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || element != null && element.equals(array[i])) {
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

    private void growArray() {
        T[] tempArray = (T[])(new Object[array.length + (array.length >> 1)]);
        System.arraycopy(array, 0, tempArray,0, array.length);
        array = (T[]) new Object[array.length + (array.length >> 1)];
        array = tempArray;
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array list range");
        }
    }
}
