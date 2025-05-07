package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkSize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkForAdd(index);
        checkSize();
        System.arraycopy(array, index, array, index + 1,
                    size - index);
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
        checkForGetAndSet(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkForGetAndSet(index);
        T oldValue = array[index];
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkForGetAndSet(index);
        T oldValue = array[index];
        if ((size - 1) > index) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        array[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element || array[i] != null && array[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add new value. "
                    + "Index:" + index + ". Size: " + size);
        }
    }

    private void checkForGetAndSet(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds. "
                    + "Index:" + index + ". Size: " + size);
        }
    }

    private void checkSize() {
        if (size == array.length) {
            resize();
        }
    }

    private Object[] resize() {
        int newSize = size + (size >> 1);
        Object[] newArray = (T[]) new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return array = (T[]) newArray;
    }
}
