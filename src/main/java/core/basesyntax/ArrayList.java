package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;

    private int size = 0;
    private Object[] array = new Object[ARRAY_SIZE];

    @Override
    public void add(T value) {
        if (size + 1 == array.length) {
            increaseArray();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index out of bounds");
        }
        if (size == index) {
            add(value);
            return;
        }
        if (size + 1 == array.length) {
            increaseArray();
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
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index out of bounds");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index out of bounds");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index out of bounds");
        }
        T removedElement = (T) array[index];
        size--;
        System.arraycopy(array, index + 1, array, index, size - index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element || (array[i] != null && array[i].equals(element))) {
                size--;
                System.arraycopy(array, i + 1, array, i, size - i);
                return element;
            }
        }
        throw new NoSuchElementException("element not exist in list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseArray() {
        Object[] newArray = new Object[size + size << 1];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}

