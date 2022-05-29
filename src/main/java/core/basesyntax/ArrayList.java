package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private int size;
    private Object[] array;

    public ArrayList() {
        this.array = new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        if (array.length == size) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        if (array.length == size) {
            grow();
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
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object o = array[index];
        if (index == array.length - 1) {
            array[index] = null;
        } else {
            System.arraycopy(array, index + 1, array, index, size - index);
        }
        size--;
        return (T) o;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null && element == null
                    || array[i] != null && array[i].equals(element)) {
                Object o = array[i];
                System.arraycopy(array, i + 1, array, i, size - i);
                size--;
                return (T) o;
            }
        }
        throw new NoSuchElementException("No such element:" + element);
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
        Object[] tmpArray = new Object[size + (size >> 1)];
        System.arraycopy(array, 0, tmpArray, 0, size);
        array = tmpArray;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
