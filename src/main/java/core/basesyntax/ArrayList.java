package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFOULT_CAPASITY = 10;
    private static final float GROW_INDEX = 1.5f;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFOULT_CAPASITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Test failed! Can't correctly add element by index - "
                            + index + "Index can be from 0 to " + size);
        }
        if (size == array.length) {
            grow();
        }
        if (index <= size) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > array.length) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            if (size == array.length) {
                grow();
            }
            array[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist");
        }
        T tmp = array[index];
        size--;
        System.arraycopy(array, index + 1, array, index, size - index);
        return tmp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i <= size; i++) {
            if (array[i] == element || (array[i] != null && array[i].equals(element))) {
                T tmp = array[i];
                System.arraycopy(array, i + 1, array, i, size - i);
                size--;
                return tmp;
            }
        }
        throw new NoSuchElementException("The element - "
                + element + "  was not found in the list");
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
        int size = Math.round(array.length * GROW_INDEX);
        T[] newArray = (T[]) new Object[size];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
}
