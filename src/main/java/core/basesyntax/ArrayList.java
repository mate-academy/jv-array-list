package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DIVIDE_BY_TWO = 2;
    private static final int INDEX_BY_ZERO = 0;
    private static final int INDEX_BY_ONE = 1;
    private static final int EMPTY = 0;
    private T[] array;
    private T[] copiedArray;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (array.length == size) {
            array = grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < INDEX_BY_ZERO) {
            throw new ArrayListIndexOutOfBoundsException(
                    "You cannot add element by negative index");
        }
        if (array.length == size + INDEX_BY_ONE) {
            array = grow();
        }
        if (index < array.length && index < size) {
            copiedArray = (T[]) new Object[array.length];
            System.arraycopy(array, INDEX_BY_ZERO, copiedArray, INDEX_BY_ZERO, index);
            copiedArray[index] = value;
            System.arraycopy(array, index, copiedArray, index + INDEX_BY_ONE, size + INDEX_BY_ONE);
            System.arraycopy(copiedArray, INDEX_BY_ZERO, array, INDEX_BY_ZERO, size + INDEX_BY_ONE);
            size++;
            return;
        } else if (index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "You cannot add element by not existent index");
        }
        array[size] = value;
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
        if (index < size && index >= INDEX_BY_ZERO) {
            return array[index];
        }
        throw new ArrayListIndexOutOfBoundsException("There is not element by index: " + index);
    }

    @Override
    public void set(T value, int index) {
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "You cannot add element by not existent index");
        } else if (index < INDEX_BY_ZERO) {
            throw new ArrayListIndexOutOfBoundsException(
                    "You cannot add element by negative index");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if ((index + INDEX_BY_ONE) == size) {
            copiedArray = (T[]) new Object[array.length];
            System.arraycopy(array, index, copiedArray, INDEX_BY_ZERO, size - index);
            array[index] = null;
            size--;
            return copiedArray[INDEX_BY_ZERO];
        }
        if (index < size && index >= INDEX_BY_ZERO) {
            copiedArray = (T[]) new Object[array.length];
            System.arraycopy(array, index, copiedArray, INDEX_BY_ZERO, size - index);
            System.arraycopy(copiedArray, INDEX_BY_ONE, array, index, size--);
            return copiedArray[INDEX_BY_ZERO];
        }
        if (index < INDEX_BY_ZERO) {
            throw new ArrayListIndexOutOfBoundsException(
                    "You cannot remove element by negative index");
        }
        throw new ArrayListIndexOutOfBoundsException(
                "You cannot remove element by not existent index");
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element || array[i] != null && array[i].equals(element)) {
                copiedArray = (T[]) new Object[DEFAULT_CAPACITY];
                System.arraycopy(array, i, copiedArray, INDEX_BY_ZERO, size - i);
                System.arraycopy(copiedArray, INDEX_BY_ONE, array, i, size--);
                return copiedArray[INDEX_BY_ZERO];
            }
        }
        throw new NoSuchElementException("Given element does not exist. Try to write correct");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == EMPTY) {
            return true;
        }
        return false;
    }

    private T[] grow() {
        if ((size + INDEX_BY_ONE) == array.length) {
            array = resize();
        } else if (size == array.length) {
            array = resize();
        }
        return array;
    }

    private T[] resize() {
        copiedArray = (T[]) new Object[array.length];
        System.arraycopy(array, INDEX_BY_ZERO, copiedArray, INDEX_BY_ZERO, array.length);
        array = (T[]) new Object[array.length + (array.length / DIVIDE_BY_TWO)];
        System.arraycopy(copiedArray, INDEX_BY_ZERO, array, INDEX_BY_ZERO, copiedArray.length);
        return array;
    }
}
