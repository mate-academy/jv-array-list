package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            resize(array.length + array.length / 2);
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size == array.length - 1) {
            resize(array.length + array.length / 2);
        }
        rangeCheckForAdd(index);
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
        if (isEmpty() || index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " not found");
        } else {
            return (T) array[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (isEmpty() || index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " not found");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isEmpty() || index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " not found");
        }
        T removedElement = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (element == null ? array[i] == element : element.equals(array[i])) {
                System.arraycopy(array, i + 1, array, i, size - i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Elements not found" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newSize) {
        Object[] newArray = new Object[array.length + array.length / 2];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound " + index);
        }
    }
}
