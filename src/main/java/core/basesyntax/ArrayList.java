package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    public void setSize(int size) {
        this.size = size;
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
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("This index " + index + " is not exist!");
        }
        if (index == size) {
            add(value);
            return;
        }
        if (size >= array.length) {
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
        T removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, --size - index);
        return removedElement;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if ((element != null && element.equals(array[i])) || array[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("This element " + element + " is not exist!");
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
        int newCapacity = size + (size >> 1);
        T[] growArray = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, growArray, 0, size);
        array = growArray;
    }

    private void checkIndex(int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index " + index + " is not exist!");
        }
    }
}

