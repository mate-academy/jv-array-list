package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_VALUE = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[INITIAL_VALUE];
    }

    @Override
    public void add(T value) {
        checkSize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        checkSize();
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
        checkForIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkForIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkForIndex(index);
        T  removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element || element != null && element.equals(array[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element exists");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkForIndex(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void increasingAnArray() {
        T[] newArray = (T[]) new Object[size + (size / 2)];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private void checkSize() {
        if (size == array.length) {
            increasingAnArray();
        }
    }
}
