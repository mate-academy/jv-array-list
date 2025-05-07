package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPASITY = 10;
    private static final double INCREASE_RATE = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[START_CAPASITY];
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
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            if (array.length == size) {
                grow();
            }
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        }
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
        T removed = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = findIndex(element);
        return remove(index);
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
        T[] listNew = (T[]) new Object[(int) (size * INCREASE_RATE)];
        System.arraycopy(array, 0, listNew, 0, size);
        array = listNew;
    }

    private void checkIndex(int index) {
        if (index < 0 || (index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
    }

    private int findIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || (array[i] != null && array[i].equals(element))) {
                return i;
            }
        }
        throw new NoSuchElementException("Element " + element + " not found in List");
    }
}
