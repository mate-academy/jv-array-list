package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
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
        growIfArrayFull();
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
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        array[--size] = null;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element || array[i] != null
                    && array[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There isn't \"" + element + "\" element in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size >= array.length) {
            resize();
        }
    }

    private void resize() {
        T[] tempArray = (T[]) new Object[array.length + (array.length / 2)];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        array = tempArray;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be less than zero: " + index);
        } else if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index. Max index in array:"
                    + (size - 1) + " yours: " + index);
        }
    }
}
