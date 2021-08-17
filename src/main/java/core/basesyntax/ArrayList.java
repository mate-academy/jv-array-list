package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size = 0;
    private Object[] array;

    public ArrayList() {
        array = new Object[DEFAULT_SIZE];
    }

    private Object[] resize() {
        int newSize = (int) (array.length * 1.5);
        Object[] newArray = new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index. "
                    + "Index is out of bounds of array");
        }
    }

    private void checkIndexWithEquals(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index. "
                    + "Index is out of bounds of array");
        }
    }

    @Override
    public void add(T value) {
        if (size == array.length - 1) {
            array = resize();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (size == array.length - 1) {
            array = resize();
        }
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
        checkIndexWithEquals(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexWithEquals(index);
        if (index < size) {
            array[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        checkIndexWithEquals(index);
        T removedValue = (T) array[index];
        int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(array, index + 1, array, index, newSize - index);
        }
        array[size = newSize] = null;

        return removedValue;
    }

    @Override
    public T remove(T element) {
        T removedElement = null;
        for (int i = 0; i < size(); i++) {
            if (element == null && array[i] == null) {
                removedElement = remove(i);
                return removedElement;
            }
            if (array[i] != null && array[i].equals(element)) {
                removedElement = remove(i);
                return removedElement;
            }
        }

        throw new NoSuchElementException("Invalid entry element. "
                + "No such element exists");
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
